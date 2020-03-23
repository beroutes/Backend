package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Seguidores;
import com.beroutes.app.repository.SeguidoresRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SeguidoresResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SeguidoresResourceIT {

    @Autowired
    private SeguidoresRepository seguidoresRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSeguidoresMockMvc;

    private Seguidores seguidores;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Seguidores createEntity(EntityManager em) {
        Seguidores seguidores = new Seguidores();
        return seguidores;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Seguidores createUpdatedEntity(EntityManager em) {
        Seguidores seguidores = new Seguidores();
        return seguidores;
    }

    @BeforeEach
    public void initTest() {
        seguidores = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeguidores() throws Exception {
        int databaseSizeBeforeCreate = seguidoresRepository.findAll().size();

        // Create the Seguidores
        restSeguidoresMockMvc.perform(post("/api/seguidores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seguidores)))
            .andExpect(status().isCreated());

        // Validate the Seguidores in the database
        List<Seguidores> seguidoresList = seguidoresRepository.findAll();
        assertThat(seguidoresList).hasSize(databaseSizeBeforeCreate + 1);
        Seguidores testSeguidores = seguidoresList.get(seguidoresList.size() - 1);
    }

    @Test
    @Transactional
    public void createSeguidoresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = seguidoresRepository.findAll().size();

        // Create the Seguidores with an existing ID
        seguidores.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeguidoresMockMvc.perform(post("/api/seguidores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seguidores)))
            .andExpect(status().isBadRequest());

        // Validate the Seguidores in the database
        List<Seguidores> seguidoresList = seguidoresRepository.findAll();
        assertThat(seguidoresList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSeguidores() throws Exception {
        // Initialize the database
        seguidoresRepository.saveAndFlush(seguidores);

        // Get all the seguidoresList
        restSeguidoresMockMvc.perform(get("/api/seguidores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(seguidores.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getSeguidores() throws Exception {
        // Initialize the database
        seguidoresRepository.saveAndFlush(seguidores);

        // Get the seguidores
        restSeguidoresMockMvc.perform(get("/api/seguidores/{id}", seguidores.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(seguidores.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSeguidores() throws Exception {
        // Get the seguidores
        restSeguidoresMockMvc.perform(get("/api/seguidores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeguidores() throws Exception {
        // Initialize the database
        seguidoresRepository.saveAndFlush(seguidores);

        int databaseSizeBeforeUpdate = seguidoresRepository.findAll().size();

        // Update the seguidores
        Seguidores updatedSeguidores = seguidoresRepository.findById(seguidores.getId()).get();
        // Disconnect from session so that the updates on updatedSeguidores are not directly saved in db
        em.detach(updatedSeguidores);

        restSeguidoresMockMvc.perform(put("/api/seguidores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSeguidores)))
            .andExpect(status().isOk());

        // Validate the Seguidores in the database
        List<Seguidores> seguidoresList = seguidoresRepository.findAll();
        assertThat(seguidoresList).hasSize(databaseSizeBeforeUpdate);
        Seguidores testSeguidores = seguidoresList.get(seguidoresList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingSeguidores() throws Exception {
        int databaseSizeBeforeUpdate = seguidoresRepository.findAll().size();

        // Create the Seguidores

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSeguidoresMockMvc.perform(put("/api/seguidores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seguidores)))
            .andExpect(status().isBadRequest());

        // Validate the Seguidores in the database
        List<Seguidores> seguidoresList = seguidoresRepository.findAll();
        assertThat(seguidoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSeguidores() throws Exception {
        // Initialize the database
        seguidoresRepository.saveAndFlush(seguidores);

        int databaseSizeBeforeDelete = seguidoresRepository.findAll().size();

        // Delete the seguidores
        restSeguidoresMockMvc.perform(delete("/api/seguidores/{id}", seguidores.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Seguidores> seguidoresList = seguidoresRepository.findAll();
        assertThat(seguidoresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
