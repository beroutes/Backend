package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Valoraciones;
import com.beroutes.app.repository.ValoracionesRepository;

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
 * Integration tests for the {@link ValoracionesResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ValoracionesResourceIT {

    private static final Integer DEFAULT_ESTRELLAS = 1;
    private static final Integer UPDATED_ESTRELLAS = 2;

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

    @Autowired
    private ValoracionesRepository valoracionesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restValoracionesMockMvc;

    private Valoraciones valoraciones;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Valoraciones createEntity(EntityManager em) {
        Valoraciones valoraciones = new Valoraciones()
            .estrellas(DEFAULT_ESTRELLAS)
            .comentarios(DEFAULT_COMENTARIOS);
        return valoraciones;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Valoraciones createUpdatedEntity(EntityManager em) {
        Valoraciones valoraciones = new Valoraciones()
            .estrellas(UPDATED_ESTRELLAS)
            .comentarios(UPDATED_COMENTARIOS);
        return valoraciones;
    }

    @BeforeEach
    public void initTest() {
        valoraciones = createEntity(em);
    }

    @Test
    @Transactional
    public void createValoraciones() throws Exception {
        int databaseSizeBeforeCreate = valoracionesRepository.findAll().size();

        // Create the Valoraciones
        restValoracionesMockMvc.perform(post("/api/valoraciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(valoraciones)))
            .andExpect(status().isCreated());

        // Validate the Valoraciones in the database
        List<Valoraciones> valoracionesList = valoracionesRepository.findAll();
        assertThat(valoracionesList).hasSize(databaseSizeBeforeCreate + 1);
        Valoraciones testValoraciones = valoracionesList.get(valoracionesList.size() - 1);
        assertThat(testValoraciones.getEstrellas()).isEqualTo(DEFAULT_ESTRELLAS);
        assertThat(testValoraciones.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
    }

    @Test
    @Transactional
    public void createValoracionesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = valoracionesRepository.findAll().size();

        // Create the Valoraciones with an existing ID
        valoraciones.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValoracionesMockMvc.perform(post("/api/valoraciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(valoraciones)))
            .andExpect(status().isBadRequest());

        // Validate the Valoraciones in the database
        List<Valoraciones> valoracionesList = valoracionesRepository.findAll();
        assertThat(valoracionesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllValoraciones() throws Exception {
        // Initialize the database
        valoracionesRepository.saveAndFlush(valoraciones);

        // Get all the valoracionesList
        restValoracionesMockMvc.perform(get("/api/valoraciones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valoraciones.getId().intValue())))
            .andExpect(jsonPath("$.[*].estrellas").value(hasItem(DEFAULT_ESTRELLAS)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)));
    }
    
    @Test
    @Transactional
    public void getValoraciones() throws Exception {
        // Initialize the database
        valoracionesRepository.saveAndFlush(valoraciones);

        // Get the valoraciones
        restValoracionesMockMvc.perform(get("/api/valoraciones/{id}", valoraciones.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(valoraciones.getId().intValue()))
            .andExpect(jsonPath("$.estrellas").value(DEFAULT_ESTRELLAS))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS));
    }

    @Test
    @Transactional
    public void getNonExistingValoraciones() throws Exception {
        // Get the valoraciones
        restValoracionesMockMvc.perform(get("/api/valoraciones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValoraciones() throws Exception {
        // Initialize the database
        valoracionesRepository.saveAndFlush(valoraciones);

        int databaseSizeBeforeUpdate = valoracionesRepository.findAll().size();

        // Update the valoraciones
        Valoraciones updatedValoraciones = valoracionesRepository.findById(valoraciones.getId()).get();
        // Disconnect from session so that the updates on updatedValoraciones are not directly saved in db
        em.detach(updatedValoraciones);
        updatedValoraciones
            .estrellas(UPDATED_ESTRELLAS)
            .comentarios(UPDATED_COMENTARIOS);

        restValoracionesMockMvc.perform(put("/api/valoraciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedValoraciones)))
            .andExpect(status().isOk());

        // Validate the Valoraciones in the database
        List<Valoraciones> valoracionesList = valoracionesRepository.findAll();
        assertThat(valoracionesList).hasSize(databaseSizeBeforeUpdate);
        Valoraciones testValoraciones = valoracionesList.get(valoracionesList.size() - 1);
        assertThat(testValoraciones.getEstrellas()).isEqualTo(UPDATED_ESTRELLAS);
        assertThat(testValoraciones.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void updateNonExistingValoraciones() throws Exception {
        int databaseSizeBeforeUpdate = valoracionesRepository.findAll().size();

        // Create the Valoraciones

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValoracionesMockMvc.perform(put("/api/valoraciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(valoraciones)))
            .andExpect(status().isBadRequest());

        // Validate the Valoraciones in the database
        List<Valoraciones> valoracionesList = valoracionesRepository.findAll();
        assertThat(valoracionesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteValoraciones() throws Exception {
        // Initialize the database
        valoracionesRepository.saveAndFlush(valoraciones);

        int databaseSizeBeforeDelete = valoracionesRepository.findAll().size();

        // Delete the valoraciones
        restValoracionesMockMvc.perform(delete("/api/valoraciones/{id}", valoraciones.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Valoraciones> valoracionesList = valoracionesRepository.findAll();
        assertThat(valoracionesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
