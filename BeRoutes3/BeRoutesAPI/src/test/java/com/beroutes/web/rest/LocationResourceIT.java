package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.Location;
import com.beroutes.repository.LocationRepository;
import com.beroutes.repository.search.LocationSearchRepository;
import com.beroutes.service.LocationService;

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

/**
 * Integration tests for the {@link LocationResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class LocationResourceIT {

    private static final Long DEFAULT_STEP_POSITION = 1L;
    private static final Long UPDATED_STEP_POSITION = 2L;

    private static final String DEFAULT_TITLE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_LOCATION = "BBBBBBBBBB";

    private static final Long DEFAULT_X_COORDINATE = 1L;
    private static final Long UPDATED_X_COORDINATE = 2L;

    private static final Long DEFAULT_Y_COORDINATE = 1L;
    private static final Long UPDATED_Y_COORDINATE = 2L;

    private static final String DEFAULT_STEP_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_STEP_DURATION = "BBBBBBBBBB";

    private static final Long DEFAULT_QR_CODE = 1L;
    private static final Long UPDATED_QR_CODE = 2L;

    private static final String DEFAULT_QR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_QR_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.LocationSearchRepositoryMockConfiguration
     */
    @Autowired
    private LocationSearchRepository mockLocationSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocationMockMvc;

    private Location location;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createEntity(EntityManager em) {
        Location location = new Location()
            .stepPosition(DEFAULT_STEP_POSITION)
            .titleLocation(DEFAULT_TITLE_LOCATION)
            .descriptionLocation(DEFAULT_DESCRIPTION_LOCATION)
            .xCoordinate(DEFAULT_X_COORDINATE)
            .yCoordinate(DEFAULT_Y_COORDINATE)
            .stepDuration(DEFAULT_STEP_DURATION)
            .qrCode(DEFAULT_QR_CODE)
            .qrDescription(DEFAULT_QR_DESCRIPTION)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return location;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createUpdatedEntity(EntityManager em) {
        Location location = new Location()
            .stepPosition(UPDATED_STEP_POSITION)
            .titleLocation(UPDATED_TITLE_LOCATION)
            .descriptionLocation(UPDATED_DESCRIPTION_LOCATION)
            .xCoordinate(UPDATED_X_COORDINATE)
            .yCoordinate(UPDATED_Y_COORDINATE)
            .stepDuration(UPDATED_STEP_DURATION)
            .qrCode(UPDATED_QR_CODE)
            .qrDescription(UPDATED_QR_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return location;
    }

    @BeforeEach
    public void initTest() {
        location = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getStepPosition()).isEqualTo(DEFAULT_STEP_POSITION);
        assertThat(testLocation.getTitleLocation()).isEqualTo(DEFAULT_TITLE_LOCATION);
        assertThat(testLocation.getDescriptionLocation()).isEqualTo(DEFAULT_DESCRIPTION_LOCATION);
        assertThat(testLocation.getxCoordinate()).isEqualTo(DEFAULT_X_COORDINATE);
        assertThat(testLocation.getyCoordinate()).isEqualTo(DEFAULT_Y_COORDINATE);
        assertThat(testLocation.getStepDuration()).isEqualTo(DEFAULT_STEP_DURATION);
        assertThat(testLocation.getQrCode()).isEqualTo(DEFAULT_QR_CODE);
        assertThat(testLocation.getQrDescription()).isEqualTo(DEFAULT_QR_DESCRIPTION);
        assertThat(testLocation.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLocation.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);

        // Validate the Location in Elasticsearch
        verify(mockLocationSearchRepository, times(1)).save(testLocation);
    }

    @Test
    @Transactional
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location with an existing ID
        location.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);

        // Validate the Location in Elasticsearch
        verify(mockLocationSearchRepository, times(0)).save(location);
    }


    @Test
    @Transactional
    public void checkStepPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationRepository.findAll().size();
        // set the field null
        location.setStepPosition(null);

        // Create the Location, which fails.

        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationRepository.findAll().size();
        // set the field null
        location.setTitleLocation(null);

        // Create the Location, which fails.

        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationRepository.findAll().size();
        // set the field null
        location.setDescriptionLocation(null);

        // Create the Location, which fails.

        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocations() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].stepPosition").value(hasItem(DEFAULT_STEP_POSITION.intValue())))
            .andExpect(jsonPath("$.[*].titleLocation").value(hasItem(DEFAULT_TITLE_LOCATION)))
            .andExpect(jsonPath("$.[*].descriptionLocation").value(hasItem(DEFAULT_DESCRIPTION_LOCATION)))
            .andExpect(jsonPath("$.[*].xCoordinate").value(hasItem(DEFAULT_X_COORDINATE.intValue())))
            .andExpect(jsonPath("$.[*].yCoordinate").value(hasItem(DEFAULT_Y_COORDINATE.intValue())))
            .andExpect(jsonPath("$.[*].stepDuration").value(hasItem(DEFAULT_STEP_DURATION)))
            .andExpect(jsonPath("$.[*].qrCode").value(hasItem(DEFAULT_QR_CODE.intValue())))
            .andExpect(jsonPath("$.[*].qrDescription").value(hasItem(DEFAULT_QR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(location.getId().intValue()))
            .andExpect(jsonPath("$.stepPosition").value(DEFAULT_STEP_POSITION.intValue()))
            .andExpect(jsonPath("$.titleLocation").value(DEFAULT_TITLE_LOCATION))
            .andExpect(jsonPath("$.descriptionLocation").value(DEFAULT_DESCRIPTION_LOCATION))
            .andExpect(jsonPath("$.xCoordinate").value(DEFAULT_X_COORDINATE.intValue()))
            .andExpect(jsonPath("$.yCoordinate").value(DEFAULT_Y_COORDINATE.intValue()))
            .andExpect(jsonPath("$.stepDuration").value(DEFAULT_STEP_DURATION))
            .andExpect(jsonPath("$.qrCode").value(DEFAULT_QR_CODE.intValue()))
            .andExpect(jsonPath("$.qrDescription").value(DEFAULT_QR_DESCRIPTION))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocation() throws Exception {
        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocation() throws Exception {
        // Initialize the database
        locationService.save(location);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockLocationSearchRepository);

        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Update the location
        Location updatedLocation = locationRepository.findById(location.getId()).get();
        // Disconnect from session so that the updates on updatedLocation are not directly saved in db
        em.detach(updatedLocation);
        updatedLocation
            .stepPosition(UPDATED_STEP_POSITION)
            .titleLocation(UPDATED_TITLE_LOCATION)
            .descriptionLocation(UPDATED_DESCRIPTION_LOCATION)
            .xCoordinate(UPDATED_X_COORDINATE)
            .yCoordinate(UPDATED_Y_COORDINATE)
            .stepDuration(UPDATED_STEP_DURATION)
            .qrCode(UPDATED_QR_CODE)
            .qrDescription(UPDATED_QR_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocation)))
            .andExpect(status().isOk());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getStepPosition()).isEqualTo(UPDATED_STEP_POSITION);
        assertThat(testLocation.getTitleLocation()).isEqualTo(UPDATED_TITLE_LOCATION);
        assertThat(testLocation.getDescriptionLocation()).isEqualTo(UPDATED_DESCRIPTION_LOCATION);
        assertThat(testLocation.getxCoordinate()).isEqualTo(UPDATED_X_COORDINATE);
        assertThat(testLocation.getyCoordinate()).isEqualTo(UPDATED_Y_COORDINATE);
        assertThat(testLocation.getStepDuration()).isEqualTo(UPDATED_STEP_DURATION);
        assertThat(testLocation.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testLocation.getQrDescription()).isEqualTo(UPDATED_QR_DESCRIPTION);
        assertThat(testLocation.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLocation.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);

        // Validate the Location in Elasticsearch
        verify(mockLocationSearchRepository, times(1)).save(testLocation);
    }

    @Test
    @Transactional
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Create the Location

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(location)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Location in Elasticsearch
        verify(mockLocationSearchRepository, times(0)).save(location);
    }

    @Test
    @Transactional
    public void deleteLocation() throws Exception {
        // Initialize the database
        locationService.save(location);

        int databaseSizeBeforeDelete = locationRepository.findAll().size();

        // Delete the location
        restLocationMockMvc.perform(delete("/api/locations/{id}", location.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Location in Elasticsearch
        verify(mockLocationSearchRepository, times(1)).deleteById(location.getId());
    }

    @Test
    @Transactional
    public void searchLocation() throws Exception {
        // Initialize the database
        locationService.save(location);
        when(mockLocationSearchRepository.search(queryStringQuery("id:" + location.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(location), PageRequest.of(0, 1), 1));
        // Search the location
        restLocationMockMvc.perform(get("/api/_search/locations?query=id:" + location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].stepPosition").value(hasItem(DEFAULT_STEP_POSITION.intValue())))
            .andExpect(jsonPath("$.[*].titleLocation").value(hasItem(DEFAULT_TITLE_LOCATION)))
            .andExpect(jsonPath("$.[*].descriptionLocation").value(hasItem(DEFAULT_DESCRIPTION_LOCATION)))
            .andExpect(jsonPath("$.[*].xCoordinate").value(hasItem(DEFAULT_X_COORDINATE.intValue())))
            .andExpect(jsonPath("$.[*].yCoordinate").value(hasItem(DEFAULT_Y_COORDINATE.intValue())))
            .andExpect(jsonPath("$.[*].stepDuration").value(hasItem(DEFAULT_STEP_DURATION)))
            .andExpect(jsonPath("$.[*].qrCode").value(hasItem(DEFAULT_QR_CODE.intValue())))
            .andExpect(jsonPath("$.[*].qrDescription").value(hasItem(DEFAULT_QR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
}
