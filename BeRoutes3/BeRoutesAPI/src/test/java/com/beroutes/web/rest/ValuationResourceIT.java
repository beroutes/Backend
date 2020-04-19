package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.Valuation;
import com.beroutes.repository.ValuationRepository;
import com.beroutes.repository.search.ValuationSearchRepository;
import com.beroutes.service.ValuationService;

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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ValuationResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ValuationResourceIT {

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    @Autowired
    private ValuationRepository valuationRepository;

    @Autowired
    private ValuationService valuationService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.ValuationSearchRepositoryMockConfiguration
     */
    @Autowired
    private ValuationSearchRepository mockValuationSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restValuationMockMvc;

    private Valuation valuation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Valuation createEntity(EntityManager em) {
        Valuation valuation = new Valuation()
            .comment(DEFAULT_COMMENT)
            .rating(DEFAULT_RATING);
        return valuation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Valuation createUpdatedEntity(EntityManager em) {
        Valuation valuation = new Valuation()
            .comment(UPDATED_COMMENT)
            .rating(UPDATED_RATING);
        return valuation;
    }

    @BeforeEach
    public void initTest() {
        valuation = createEntity(em);
    }

    @Test
    @Transactional
    public void createValuation() throws Exception {
        int databaseSizeBeforeCreate = valuationRepository.findAll().size();

        // Create the Valuation
        restValuationMockMvc.perform(post("/api/valuations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(valuation)))
            .andExpect(status().isCreated());

        // Validate the Valuation in the database
        List<Valuation> valuationList = valuationRepository.findAll();
        assertThat(valuationList).hasSize(databaseSizeBeforeCreate + 1);
        Valuation testValuation = valuationList.get(valuationList.size() - 1);
        assertThat(testValuation.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testValuation.getRating()).isEqualTo(DEFAULT_RATING);

        // Validate the Valuation in Elasticsearch
        verify(mockValuationSearchRepository, times(1)).save(testValuation);
    }

    @Test
    @Transactional
    public void createValuationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = valuationRepository.findAll().size();

        // Create the Valuation with an existing ID
        valuation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValuationMockMvc.perform(post("/api/valuations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(valuation)))
            .andExpect(status().isBadRequest());

        // Validate the Valuation in the database
        List<Valuation> valuationList = valuationRepository.findAll();
        assertThat(valuationList).hasSize(databaseSizeBeforeCreate);

        // Validate the Valuation in Elasticsearch
        verify(mockValuationSearchRepository, times(0)).save(valuation);
    }


    @Test
    @Transactional
    public void getAllValuations() throws Exception {
        // Initialize the database
        valuationRepository.saveAndFlush(valuation);

        // Get all the valuationList
        restValuationMockMvc.perform(get("/api/valuations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valuation.getId().intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }
    
    @Test
    @Transactional
    public void getValuation() throws Exception {
        // Initialize the database
        valuationRepository.saveAndFlush(valuation);

        // Get the valuation
        restValuationMockMvc.perform(get("/api/valuations/{id}", valuation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(valuation.getId().intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING));
    }

    @Test
    @Transactional
    public void getNonExistingValuation() throws Exception {
        // Get the valuation
        restValuationMockMvc.perform(get("/api/valuations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValuation() throws Exception {
        // Initialize the database
        valuationService.save(valuation);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockValuationSearchRepository);

        int databaseSizeBeforeUpdate = valuationRepository.findAll().size();

        // Update the valuation
        Valuation updatedValuation = valuationRepository.findById(valuation.getId()).get();
        // Disconnect from session so that the updates on updatedValuation are not directly saved in db
        em.detach(updatedValuation);
        updatedValuation
            .comment(UPDATED_COMMENT)
            .rating(UPDATED_RATING);

        restValuationMockMvc.perform(put("/api/valuations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedValuation)))
            .andExpect(status().isOk());

        // Validate the Valuation in the database
        List<Valuation> valuationList = valuationRepository.findAll();
        assertThat(valuationList).hasSize(databaseSizeBeforeUpdate);
        Valuation testValuation = valuationList.get(valuationList.size() - 1);
        assertThat(testValuation.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testValuation.getRating()).isEqualTo(UPDATED_RATING);

        // Validate the Valuation in Elasticsearch
        verify(mockValuationSearchRepository, times(1)).save(testValuation);
    }

    @Test
    @Transactional
    public void updateNonExistingValuation() throws Exception {
        int databaseSizeBeforeUpdate = valuationRepository.findAll().size();

        // Create the Valuation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValuationMockMvc.perform(put("/api/valuations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(valuation)))
            .andExpect(status().isBadRequest());

        // Validate the Valuation in the database
        List<Valuation> valuationList = valuationRepository.findAll();
        assertThat(valuationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Valuation in Elasticsearch
        verify(mockValuationSearchRepository, times(0)).save(valuation);
    }

    @Test
    @Transactional
    public void deleteValuation() throws Exception {
        // Initialize the database
        valuationService.save(valuation);

        int databaseSizeBeforeDelete = valuationRepository.findAll().size();

        // Delete the valuation
        restValuationMockMvc.perform(delete("/api/valuations/{id}", valuation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Valuation> valuationList = valuationRepository.findAll();
        assertThat(valuationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Valuation in Elasticsearch
        verify(mockValuationSearchRepository, times(1)).deleteById(valuation.getId());
    }

    @Test
    @Transactional
    public void searchValuation() throws Exception {
        // Initialize the database
        valuationService.save(valuation);
        when(mockValuationSearchRepository.search(queryStringQuery("id:" + valuation.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(valuation), PageRequest.of(0, 1), 1));
        // Search the valuation
        restValuationMockMvc.perform(get("/api/_search/valuations?query=id:" + valuation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valuation.getId().intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }
}
