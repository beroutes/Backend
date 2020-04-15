package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Fotos;
import com.beroutes.app.repository.FotosRepository;

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
 * Integration tests for the {@link FotosResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FotosResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private FotosRepository fotosRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFotosMockMvc;

    private Fotos fotos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fotos createEntity(EntityManager em) {
        Fotos fotos = new Fotos()
            .titulo(DEFAULT_TITULO)
            .url(DEFAULT_URL);
        return fotos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fotos createUpdatedEntity(EntityManager em) {
        Fotos fotos = new Fotos()
            .titulo(UPDATED_TITULO)
            .url(UPDATED_URL);
        return fotos;
    }

    @BeforeEach
    public void initTest() {
        fotos = createEntity(em);
    }

    @Test
    @Transactional
    public void createFotos() throws Exception {
        int databaseSizeBeforeCreate = fotosRepository.findAll().size();

        // Create the Fotos
        restFotosMockMvc.perform(post("/api/fotos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fotos)))
            .andExpect(status().isCreated());

        // Validate the Fotos in the database
        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeCreate + 1);
        Fotos testFotos = fotosList.get(fotosList.size() - 1);
        assertThat(testFotos.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testFotos.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createFotosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fotosRepository.findAll().size();

        // Create the Fotos with an existing ID
        fotos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFotosMockMvc.perform(post("/api/fotos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fotos)))
            .andExpect(status().isBadRequest());

        // Validate the Fotos in the database
        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = fotosRepository.findAll().size();
        // set the field null
        fotos.setTitulo(null);

        // Create the Fotos, which fails.

        restFotosMockMvc.perform(post("/api/fotos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fotos)))
            .andExpect(status().isBadRequest());

        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = fotosRepository.findAll().size();
        // set the field null
        fotos.setUrl(null);

        // Create the Fotos, which fails.

        restFotosMockMvc.perform(post("/api/fotos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fotos)))
            .andExpect(status().isBadRequest());

        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFotos() throws Exception {
        // Initialize the database
        fotosRepository.saveAndFlush(fotos);

        // Get all the fotosList
        restFotosMockMvc.perform(get("/api/fotos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fotos.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getFotos() throws Exception {
        // Initialize the database
        fotosRepository.saveAndFlush(fotos);

        // Get the fotos
        restFotosMockMvc.perform(get("/api/fotos/{id}", fotos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fotos.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }

    @Test
    @Transactional
    public void getNonExistingFotos() throws Exception {
        // Get the fotos
        restFotosMockMvc.perform(get("/api/fotos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFotos() throws Exception {
        // Initialize the database
        fotosRepository.saveAndFlush(fotos);

        int databaseSizeBeforeUpdate = fotosRepository.findAll().size();

        // Update the fotos
        Fotos updatedFotos = fotosRepository.findById(fotos.getId()).get();
        // Disconnect from session so that the updates on updatedFotos are not directly saved in db
        em.detach(updatedFotos);
        updatedFotos
            .titulo(UPDATED_TITULO)
            .url(UPDATED_URL);

        restFotosMockMvc.perform(put("/api/fotos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFotos)))
            .andExpect(status().isOk());

        // Validate the Fotos in the database
        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeUpdate);
        Fotos testFotos = fotosList.get(fotosList.size() - 1);
        assertThat(testFotos.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testFotos.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingFotos() throws Exception {
        int databaseSizeBeforeUpdate = fotosRepository.findAll().size();

        // Create the Fotos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFotosMockMvc.perform(put("/api/fotos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fotos)))
            .andExpect(status().isBadRequest());

        // Validate the Fotos in the database
        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFotos() throws Exception {
        // Initialize the database
        fotosRepository.saveAndFlush(fotos);

        int databaseSizeBeforeDelete = fotosRepository.findAll().size();

        // Delete the fotos
        restFotosMockMvc.perform(delete("/api/fotos/{id}", fotos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fotos> fotosList = fotosRepository.findAll();
        assertThat(fotosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
