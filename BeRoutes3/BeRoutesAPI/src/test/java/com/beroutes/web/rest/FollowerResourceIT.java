package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.Follower;
import com.beroutes.repository.FollowerRepository;
import com.beroutes.repository.search.FollowerSearchRepository;
import com.beroutes.service.FollowerService;

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
 * Integration tests for the {@link FollowerResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class FollowerResourceIT {

    private static final Boolean DEFAULT_ACCEPTED = false;
    private static final Boolean UPDATED_ACCEPTED = true;

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private FollowerService followerService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.FollowerSearchRepositoryMockConfiguration
     */
    @Autowired
    private FollowerSearchRepository mockFollowerSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFollowerMockMvc;

    private Follower follower;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Follower createEntity(EntityManager em) {
        Follower follower = new Follower()
            .accepted(DEFAULT_ACCEPTED);
        return follower;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Follower createUpdatedEntity(EntityManager em) {
        Follower follower = new Follower()
            .accepted(UPDATED_ACCEPTED);
        return follower;
    }

    @BeforeEach
    public void initTest() {
        follower = createEntity(em);
    }

    @Test
    @Transactional
    public void createFollower() throws Exception {
        int databaseSizeBeforeCreate = followerRepository.findAll().size();

        // Create the Follower
        restFollowerMockMvc.perform(post("/api/followers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(follower)))
            .andExpect(status().isCreated());

        // Validate the Follower in the database
        List<Follower> followerList = followerRepository.findAll();
        assertThat(followerList).hasSize(databaseSizeBeforeCreate + 1);
        Follower testFollower = followerList.get(followerList.size() - 1);
        assertThat(testFollower.isAccepted()).isEqualTo(DEFAULT_ACCEPTED);

        // Validate the Follower in Elasticsearch
        verify(mockFollowerSearchRepository, times(1)).save(testFollower);
    }

    @Test
    @Transactional
    public void createFollowerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = followerRepository.findAll().size();

        // Create the Follower with an existing ID
        follower.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFollowerMockMvc.perform(post("/api/followers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(follower)))
            .andExpect(status().isBadRequest());

        // Validate the Follower in the database
        List<Follower> followerList = followerRepository.findAll();
        assertThat(followerList).hasSize(databaseSizeBeforeCreate);

        // Validate the Follower in Elasticsearch
        verify(mockFollowerSearchRepository, times(0)).save(follower);
    }


    @Test
    @Transactional
    public void getAllFollowers() throws Exception {
        // Initialize the database
        followerRepository.saveAndFlush(follower);

        // Get all the followerList
        restFollowerMockMvc.perform(get("/api/followers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(follower.getId().intValue())))
            .andExpect(jsonPath("$.[*].accepted").value(hasItem(DEFAULT_ACCEPTED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getFollower() throws Exception {
        // Initialize the database
        followerRepository.saveAndFlush(follower);

        // Get the follower
        restFollowerMockMvc.perform(get("/api/followers/{id}", follower.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(follower.getId().intValue()))
            .andExpect(jsonPath("$.accepted").value(DEFAULT_ACCEPTED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFollower() throws Exception {
        // Get the follower
        restFollowerMockMvc.perform(get("/api/followers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFollower() throws Exception {
        // Initialize the database
        followerService.save(follower);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockFollowerSearchRepository);

        int databaseSizeBeforeUpdate = followerRepository.findAll().size();

        // Update the follower
        Follower updatedFollower = followerRepository.findById(follower.getId()).get();
        // Disconnect from session so that the updates on updatedFollower are not directly saved in db
        em.detach(updatedFollower);
        updatedFollower
            .accepted(UPDATED_ACCEPTED);

        restFollowerMockMvc.perform(put("/api/followers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFollower)))
            .andExpect(status().isOk());

        // Validate the Follower in the database
        List<Follower> followerList = followerRepository.findAll();
        assertThat(followerList).hasSize(databaseSizeBeforeUpdate);
        Follower testFollower = followerList.get(followerList.size() - 1);
        assertThat(testFollower.isAccepted()).isEqualTo(UPDATED_ACCEPTED);

        // Validate the Follower in Elasticsearch
        verify(mockFollowerSearchRepository, times(1)).save(testFollower);
    }

    @Test
    @Transactional
    public void updateNonExistingFollower() throws Exception {
        int databaseSizeBeforeUpdate = followerRepository.findAll().size();

        // Create the Follower

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFollowerMockMvc.perform(put("/api/followers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(follower)))
            .andExpect(status().isBadRequest());

        // Validate the Follower in the database
        List<Follower> followerList = followerRepository.findAll();
        assertThat(followerList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Follower in Elasticsearch
        verify(mockFollowerSearchRepository, times(0)).save(follower);
    }

    @Test
    @Transactional
    public void deleteFollower() throws Exception {
        // Initialize the database
        followerService.save(follower);

        int databaseSizeBeforeDelete = followerRepository.findAll().size();

        // Delete the follower
        restFollowerMockMvc.perform(delete("/api/followers/{id}", follower.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Follower> followerList = followerRepository.findAll();
        assertThat(followerList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Follower in Elasticsearch
        verify(mockFollowerSearchRepository, times(1)).deleteById(follower.getId());
    }

    @Test
    @Transactional
    public void searchFollower() throws Exception {
        // Initialize the database
        followerService.save(follower);
        when(mockFollowerSearchRepository.search(queryStringQuery("id:" + follower.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(follower), PageRequest.of(0, 1), 1));
        // Search the follower
        restFollowerMockMvc.perform(get("/api/_search/followers?query=id:" + follower.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(follower.getId().intValue())))
            .andExpect(jsonPath("$.[*].accepted").value(hasItem(DEFAULT_ACCEPTED.booleanValue())));
    }
}
