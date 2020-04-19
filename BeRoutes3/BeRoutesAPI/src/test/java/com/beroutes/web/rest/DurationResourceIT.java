package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.Duration;
import com.beroutes.repository.DurationRepository;
import com.beroutes.repository.search.DurationSearchRepository;
import com.beroutes.service.DurationService;

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
 * Integration tests for the {@link DurationResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DurationResourceIT {

    private static final String DEFAULT_DESCRIPTION_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_DURATION = "BBBBBBBBBB";

    private static final Long DEFAULT_MINUTES = 1L;
    private static final Long UPDATED_MINUTES = 2L;

    private static final Long DEFAULT_HOURS = 1L;
    private static final Long UPDATED_HOURS = 2L;

    private static final Long DEFAULT_DAYS = 1L;
    private static final Long UPDATED_DAYS = 2L;

    private static final Long DEFAULT_WEEKS = 1L;
    private static final Long UPDATED_WEEKS = 2L;

    private static final Long DEFAULT_YEARS = 1L;
    private static final Long UPDATED_YEARS = 2L;

    @Autowired
    private DurationRepository durationRepository;

    @Autowired
    private DurationService durationService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.DurationSearchRepositoryMockConfiguration
     */
    @Autowired
    private DurationSearchRepository mockDurationSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDurationMockMvc;

    private Duration duration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Duration createEntity(EntityManager em) {
        Duration duration = new Duration()
            .descriptionDuration(DEFAULT_DESCRIPTION_DURATION)
            .minutes(DEFAULT_MINUTES)
            .hours(DEFAULT_HOURS)
            .days(DEFAULT_DAYS)
            .weeks(DEFAULT_WEEKS)
            .years(DEFAULT_YEARS);
        return duration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Duration createUpdatedEntity(EntityManager em) {
        Duration duration = new Duration()
            .descriptionDuration(UPDATED_DESCRIPTION_DURATION)
            .minutes(UPDATED_MINUTES)
            .hours(UPDATED_HOURS)
            .days(UPDATED_DAYS)
            .weeks(UPDATED_WEEKS)
            .years(UPDATED_YEARS);
        return duration;
    }

    @BeforeEach
    public void initTest() {
        duration = createEntity(em);
    }

    @Test
    @Transactional
    public void createDuration() throws Exception {
        int databaseSizeBeforeCreate = durationRepository.findAll().size();

        // Create the Duration
        restDurationMockMvc.perform(post("/api/durations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(duration)))
            .andExpect(status().isCreated());

        // Validate the Duration in the database
        List<Duration> durationList = durationRepository.findAll();
        assertThat(durationList).hasSize(databaseSizeBeforeCreate + 1);
        Duration testDuration = durationList.get(durationList.size() - 1);
        assertThat(testDuration.getDescriptionDuration()).isEqualTo(DEFAULT_DESCRIPTION_DURATION);
        assertThat(testDuration.getMinutes()).isEqualTo(DEFAULT_MINUTES);
        assertThat(testDuration.getHours()).isEqualTo(DEFAULT_HOURS);
        assertThat(testDuration.getDays()).isEqualTo(DEFAULT_DAYS);
        assertThat(testDuration.getWeeks()).isEqualTo(DEFAULT_WEEKS);
        assertThat(testDuration.getYears()).isEqualTo(DEFAULT_YEARS);

        // Validate the Duration in Elasticsearch
        verify(mockDurationSearchRepository, times(1)).save(testDuration);
    }

    @Test
    @Transactional
    public void createDurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = durationRepository.findAll().size();

        // Create the Duration with an existing ID
        duration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDurationMockMvc.perform(post("/api/durations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(duration)))
            .andExpect(status().isBadRequest());

        // Validate the Duration in the database
        List<Duration> durationList = durationRepository.findAll();
        assertThat(durationList).hasSize(databaseSizeBeforeCreate);

        // Validate the Duration in Elasticsearch
        verify(mockDurationSearchRepository, times(0)).save(duration);
    }


    @Test
    @Transactional
    public void getAllDurations() throws Exception {
        // Initialize the database
        durationRepository.saveAndFlush(duration);

        // Get all the durationList
        restDurationMockMvc.perform(get("/api/durations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duration.getId().intValue())))
            .andExpect(jsonPath("$.[*].descriptionDuration").value(hasItem(DEFAULT_DESCRIPTION_DURATION)))
            .andExpect(jsonPath("$.[*].minutes").value(hasItem(DEFAULT_MINUTES.intValue())))
            .andExpect(jsonPath("$.[*].hours").value(hasItem(DEFAULT_HOURS.intValue())))
            .andExpect(jsonPath("$.[*].days").value(hasItem(DEFAULT_DAYS.intValue())))
            .andExpect(jsonPath("$.[*].weeks").value(hasItem(DEFAULT_WEEKS.intValue())))
            .andExpect(jsonPath("$.[*].years").value(hasItem(DEFAULT_YEARS.intValue())));
    }
    
    @Test
    @Transactional
    public void getDuration() throws Exception {
        // Initialize the database
        durationRepository.saveAndFlush(duration);

        // Get the duration
        restDurationMockMvc.perform(get("/api/durations/{id}", duration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(duration.getId().intValue()))
            .andExpect(jsonPath("$.descriptionDuration").value(DEFAULT_DESCRIPTION_DURATION))
            .andExpect(jsonPath("$.minutes").value(DEFAULT_MINUTES.intValue()))
            .andExpect(jsonPath("$.hours").value(DEFAULT_HOURS.intValue()))
            .andExpect(jsonPath("$.days").value(DEFAULT_DAYS.intValue()))
            .andExpect(jsonPath("$.weeks").value(DEFAULT_WEEKS.intValue()))
            .andExpect(jsonPath("$.years").value(DEFAULT_YEARS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDuration() throws Exception {
        // Get the duration
        restDurationMockMvc.perform(get("/api/durations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDuration() throws Exception {
        // Initialize the database
        durationService.save(duration);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockDurationSearchRepository);

        int databaseSizeBeforeUpdate = durationRepository.findAll().size();

        // Update the duration
        Duration updatedDuration = durationRepository.findById(duration.getId()).get();
        // Disconnect from session so that the updates on updatedDuration are not directly saved in db
        em.detach(updatedDuration);
        updatedDuration
            .descriptionDuration(UPDATED_DESCRIPTION_DURATION)
            .minutes(UPDATED_MINUTES)
            .hours(UPDATED_HOURS)
            .days(UPDATED_DAYS)
            .weeks(UPDATED_WEEKS)
            .years(UPDATED_YEARS);

        restDurationMockMvc.perform(put("/api/durations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDuration)))
            .andExpect(status().isOk());

        // Validate the Duration in the database
        List<Duration> durationList = durationRepository.findAll();
        assertThat(durationList).hasSize(databaseSizeBeforeUpdate);
        Duration testDuration = durationList.get(durationList.size() - 1);
        assertThat(testDuration.getDescriptionDuration()).isEqualTo(UPDATED_DESCRIPTION_DURATION);
        assertThat(testDuration.getMinutes()).isEqualTo(UPDATED_MINUTES);
        assertThat(testDuration.getHours()).isEqualTo(UPDATED_HOURS);
        assertThat(testDuration.getDays()).isEqualTo(UPDATED_DAYS);
        assertThat(testDuration.getWeeks()).isEqualTo(UPDATED_WEEKS);
        assertThat(testDuration.getYears()).isEqualTo(UPDATED_YEARS);

        // Validate the Duration in Elasticsearch
        verify(mockDurationSearchRepository, times(1)).save(testDuration);
    }

    @Test
    @Transactional
    public void updateNonExistingDuration() throws Exception {
        int databaseSizeBeforeUpdate = durationRepository.findAll().size();

        // Create the Duration

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDurationMockMvc.perform(put("/api/durations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(duration)))
            .andExpect(status().isBadRequest());

        // Validate the Duration in the database
        List<Duration> durationList = durationRepository.findAll();
        assertThat(durationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Duration in Elasticsearch
        verify(mockDurationSearchRepository, times(0)).save(duration);
    }

    @Test
    @Transactional
    public void deleteDuration() throws Exception {
        // Initialize the database
        durationService.save(duration);

        int databaseSizeBeforeDelete = durationRepository.findAll().size();

        // Delete the duration
        restDurationMockMvc.perform(delete("/api/durations/{id}", duration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Duration> durationList = durationRepository.findAll();
        assertThat(durationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Duration in Elasticsearch
        verify(mockDurationSearchRepository, times(1)).deleteById(duration.getId());
    }

    @Test
    @Transactional
    public void searchDuration() throws Exception {
        // Initialize the database
        durationService.save(duration);
        when(mockDurationSearchRepository.search(queryStringQuery("id:" + duration.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(duration), PageRequest.of(0, 1), 1));
        // Search the duration
        restDurationMockMvc.perform(get("/api/_search/durations?query=id:" + duration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duration.getId().intValue())))
            .andExpect(jsonPath("$.[*].descriptionDuration").value(hasItem(DEFAULT_DESCRIPTION_DURATION)))
            .andExpect(jsonPath("$.[*].minutes").value(hasItem(DEFAULT_MINUTES.intValue())))
            .andExpect(jsonPath("$.[*].hours").value(hasItem(DEFAULT_HOURS.intValue())))
            .andExpect(jsonPath("$.[*].days").value(hasItem(DEFAULT_DAYS.intValue())))
            .andExpect(jsonPath("$.[*].weeks").value(hasItem(DEFAULT_WEEKS.intValue())))
            .andExpect(jsonPath("$.[*].years").value(hasItem(DEFAULT_YEARS.intValue())));
    }
}
