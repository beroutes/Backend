package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.TravelRoute;
import com.beroutes.repository.TravelRouteRepository;
import com.beroutes.repository.search.TravelRouteSearchRepository;
import com.beroutes.service.TravelRouteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.beroutes.domain.enumeration.Season;
/**
 * Integration tests for the {@link TravelRouteResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TravelRouteResourceIT {

    private static final String DEFAULT_TITLE_ROUTE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_ROUTE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION = "BBBBBBBBBB";

    private static final Season DEFAULT_SEASON = Season.SPRING;
    private static final Season UPDATED_SEASON = Season.SUMMER;

    private static final Double DEFAULT_BUDGET = 1D;
    private static final Double UPDATED_BUDGET = 2D;

    private static final String DEFAULT_DESCRIPTION_ROUTE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_ROUTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelRouteService travelRouteService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.TravelRouteSearchRepositoryMockConfiguration
     */
    @Autowired
    private TravelRouteSearchRepository mockTravelRouteSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTravelRouteMockMvc;

    private TravelRoute travelRoute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelRoute createEntity(EntityManager em) {
        TravelRoute travelRoute = new TravelRoute()
            .titleRoute(DEFAULT_TITLE_ROUTE)
            .destination(DEFAULT_DESTINATION)
            .season(DEFAULT_SEASON)
            .budget(DEFAULT_BUDGET)
            .descriptionRoute(DEFAULT_DESCRIPTION_ROUTE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return travelRoute;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelRoute createUpdatedEntity(EntityManager em) {
        TravelRoute travelRoute = new TravelRoute()
            .titleRoute(UPDATED_TITLE_ROUTE)
            .destination(UPDATED_DESTINATION)
            .season(UPDATED_SEASON)
            .budget(UPDATED_BUDGET)
            .descriptionRoute(UPDATED_DESCRIPTION_ROUTE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return travelRoute;
    }

    @BeforeEach
    public void initTest() {
        travelRoute = createEntity(em);
    }

    @Test
    @Transactional
    public void createTravelRoute() throws Exception {
        int databaseSizeBeforeCreate = travelRouteRepository.findAll().size();

        // Create the TravelRoute
        restTravelRouteMockMvc.perform(post("/api/travel-routes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelRoute)))
            .andExpect(status().isCreated());

        // Validate the TravelRoute in the database
        List<TravelRoute> travelRouteList = travelRouteRepository.findAll();
        assertThat(travelRouteList).hasSize(databaseSizeBeforeCreate + 1);
        TravelRoute testTravelRoute = travelRouteList.get(travelRouteList.size() - 1);
        assertThat(testTravelRoute.getTitleRoute()).isEqualTo(DEFAULT_TITLE_ROUTE);
        assertThat(testTravelRoute.getDestination()).isEqualTo(DEFAULT_DESTINATION);
        assertThat(testTravelRoute.getSeason()).isEqualTo(DEFAULT_SEASON);
        assertThat(testTravelRoute.getBudget()).isEqualTo(DEFAULT_BUDGET);
        assertThat(testTravelRoute.getDescriptionRoute()).isEqualTo(DEFAULT_DESCRIPTION_ROUTE);
        assertThat(testTravelRoute.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTravelRoute.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);

        // Validate the TravelRoute in Elasticsearch
        verify(mockTravelRouteSearchRepository, times(1)).save(testTravelRoute);
    }

    @Test
    @Transactional
    public void createTravelRouteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = travelRouteRepository.findAll().size();

        // Create the TravelRoute with an existing ID
        travelRoute.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTravelRouteMockMvc.perform(post("/api/travel-routes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelRoute)))
            .andExpect(status().isBadRequest());

        // Validate the TravelRoute in the database
        List<TravelRoute> travelRouteList = travelRouteRepository.findAll();
        assertThat(travelRouteList).hasSize(databaseSizeBeforeCreate);

        // Validate the TravelRoute in Elasticsearch
        verify(mockTravelRouteSearchRepository, times(0)).save(travelRoute);
    }


    @Test
    @Transactional
    public void getAllTravelRoutes() throws Exception {
        // Initialize the database
        travelRouteRepository.saveAndFlush(travelRoute);

        // Get all the travelRouteList
        restTravelRouteMockMvc.perform(get("/api/travel-routes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(travelRoute.getId().intValue())))
            .andExpect(jsonPath("$.[*].titleRoute").value(hasItem(DEFAULT_TITLE_ROUTE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].season").value(hasItem(DEFAULT_SEASON.toString())))
            .andExpect(jsonPath("$.[*].budget").value(hasItem(DEFAULT_BUDGET.doubleValue())))
            .andExpect(jsonPath("$.[*].descriptionRoute").value(hasItem(DEFAULT_DESCRIPTION_ROUTE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getTravelRoute() throws Exception {
        // Initialize the database
        travelRouteRepository.saveAndFlush(travelRoute);

        // Get the travelRoute
        restTravelRouteMockMvc.perform(get("/api/travel-routes/{id}", travelRoute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(travelRoute.getId().intValue()))
            .andExpect(jsonPath("$.titleRoute").value(DEFAULT_TITLE_ROUTE))
            .andExpect(jsonPath("$.destination").value(DEFAULT_DESTINATION))
            .andExpect(jsonPath("$.season").value(DEFAULT_SEASON.toString()))
            .andExpect(jsonPath("$.budget").value(DEFAULT_BUDGET.doubleValue()))
            .andExpect(jsonPath("$.descriptionRoute").value(DEFAULT_DESCRIPTION_ROUTE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTravelRoute() throws Exception {
        // Get the travelRoute
        restTravelRouteMockMvc.perform(get("/api/travel-routes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTravelRoute() throws Exception {
        // Initialize the database
        travelRouteService.save(travelRoute);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockTravelRouteSearchRepository);

        int databaseSizeBeforeUpdate = travelRouteRepository.findAll().size();

        // Update the travelRoute
        TravelRoute updatedTravelRoute = travelRouteRepository.findById(travelRoute.getId()).get();
        // Disconnect from session so that the updates on updatedTravelRoute are not directly saved in db
        em.detach(updatedTravelRoute);
        updatedTravelRoute
            .titleRoute(UPDATED_TITLE_ROUTE)
            .destination(UPDATED_DESTINATION)
            .season(UPDATED_SEASON)
            .budget(UPDATED_BUDGET)
            .descriptionRoute(UPDATED_DESCRIPTION_ROUTE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restTravelRouteMockMvc.perform(put("/api/travel-routes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTravelRoute)))
            .andExpect(status().isOk());

        // Validate the TravelRoute in the database
        List<TravelRoute> travelRouteList = travelRouteRepository.findAll();
        assertThat(travelRouteList).hasSize(databaseSizeBeforeUpdate);
        TravelRoute testTravelRoute = travelRouteList.get(travelRouteList.size() - 1);
        assertThat(testTravelRoute.getTitleRoute()).isEqualTo(UPDATED_TITLE_ROUTE);
        assertThat(testTravelRoute.getDestination()).isEqualTo(UPDATED_DESTINATION);
        assertThat(testTravelRoute.getSeason()).isEqualTo(UPDATED_SEASON);
        assertThat(testTravelRoute.getBudget()).isEqualTo(UPDATED_BUDGET);
        assertThat(testTravelRoute.getDescriptionRoute()).isEqualTo(UPDATED_DESCRIPTION_ROUTE);
        assertThat(testTravelRoute.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTravelRoute.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);

        // Validate the TravelRoute in Elasticsearch
        verify(mockTravelRouteSearchRepository, times(1)).save(testTravelRoute);
    }

    @Test
    @Transactional
    public void updateNonExistingTravelRoute() throws Exception {
        int databaseSizeBeforeUpdate = travelRouteRepository.findAll().size();

        // Create the TravelRoute

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTravelRouteMockMvc.perform(put("/api/travel-routes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelRoute)))
            .andExpect(status().isBadRequest());

        // Validate the TravelRoute in the database
        List<TravelRoute> travelRouteList = travelRouteRepository.findAll();
        assertThat(travelRouteList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TravelRoute in Elasticsearch
        verify(mockTravelRouteSearchRepository, times(0)).save(travelRoute);
    }

    @Test
    @Transactional
    public void deleteTravelRoute() throws Exception {
        // Initialize the database
        travelRouteService.save(travelRoute);

        int databaseSizeBeforeDelete = travelRouteRepository.findAll().size();

        // Delete the travelRoute
        restTravelRouteMockMvc.perform(delete("/api/travel-routes/{id}", travelRoute.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TravelRoute> travelRouteList = travelRouteRepository.findAll();
        assertThat(travelRouteList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TravelRoute in Elasticsearch
        verify(mockTravelRouteSearchRepository, times(1)).deleteById(travelRoute.getId());
    }

    @Test
    @Transactional
    public void searchTravelRoute() throws Exception {
        // Initialize the database
        travelRouteService.save(travelRoute);
        when(mockTravelRouteSearchRepository.search(queryStringQuery("id:" + travelRoute.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(travelRoute), PageRequest.of(0, 1), 1));
        // Search the travelRoute
        restTravelRouteMockMvc.perform(get("/api/_search/travel-routes?query=id:" + travelRoute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(travelRoute.getId().intValue())))
            .andExpect(jsonPath("$.[*].titleRoute").value(hasItem(DEFAULT_TITLE_ROUTE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].season").value(hasItem(DEFAULT_SEASON.toString())))
            .andExpect(jsonPath("$.[*].budget").value(hasItem(DEFAULT_BUDGET.doubleValue())))
            .andExpect(jsonPath("$.[*].descriptionRoute").value(hasItem(DEFAULT_DESCRIPTION_ROUTE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
}
