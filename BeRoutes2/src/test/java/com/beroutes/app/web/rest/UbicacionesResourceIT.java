package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Ubicaciones;
import com.beroutes.app.repository.UbicacionesRepository;

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
 * Integration tests for the {@link UbicacionesResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UbicacionesResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Long DEFAULT_COORDENADA_X = 1L;
    private static final Long UPDATED_COORDENADA_X = 2L;

    private static final Long DEFAULT_COORDENADA_Y = 1L;
    private static final Long UPDATED_COORDENADA_Y = 2L;

    private static final Double DEFAULT_DURACION = 1D;
    private static final Double UPDATED_DURACION = 2D;

    private static final String DEFAULT_QR = "AAAAAAAAAA";
    private static final String UPDATED_QR = "BBBBBBBBBB";

    @Autowired
    private UbicacionesRepository ubicacionesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUbicacionesMockMvc;

    private Ubicaciones ubicaciones;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ubicaciones createEntity(EntityManager em) {
        Ubicaciones ubicaciones = new Ubicaciones()
            .titulo(DEFAULT_TITULO)
            .descripcion(DEFAULT_DESCRIPCION)
            .coordenadaX(DEFAULT_COORDENADA_X)
            .coordenadaY(DEFAULT_COORDENADA_Y)
            .duracion(DEFAULT_DURACION)
            .qr(DEFAULT_QR);
        return ubicaciones;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ubicaciones createUpdatedEntity(EntityManager em) {
        Ubicaciones ubicaciones = new Ubicaciones()
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .coordenadaX(UPDATED_COORDENADA_X)
            .coordenadaY(UPDATED_COORDENADA_Y)
            .duracion(UPDATED_DURACION)
            .qr(UPDATED_QR);
        return ubicaciones;
    }

    @BeforeEach
    public void initTest() {
        ubicaciones = createEntity(em);
    }

    @Test
    @Transactional
    public void createUbicaciones() throws Exception {
        int databaseSizeBeforeCreate = ubicacionesRepository.findAll().size();

        // Create the Ubicaciones
        restUbicacionesMockMvc.perform(post("/api/ubicaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ubicaciones)))
            .andExpect(status().isCreated());

        // Validate the Ubicaciones in the database
        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeCreate + 1);
        Ubicaciones testUbicaciones = ubicacionesList.get(ubicacionesList.size() - 1);
        assertThat(testUbicaciones.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testUbicaciones.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testUbicaciones.getCoordenadaX()).isEqualTo(DEFAULT_COORDENADA_X);
        assertThat(testUbicaciones.getCoordenadaY()).isEqualTo(DEFAULT_COORDENADA_Y);
        assertThat(testUbicaciones.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testUbicaciones.getQr()).isEqualTo(DEFAULT_QR);
    }

    @Test
    @Transactional
    public void createUbicacionesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ubicacionesRepository.findAll().size();

        // Create the Ubicaciones with an existing ID
        ubicaciones.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUbicacionesMockMvc.perform(post("/api/ubicaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ubicaciones)))
            .andExpect(status().isBadRequest());

        // Validate the Ubicaciones in the database
        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = ubicacionesRepository.findAll().size();
        // set the field null
        ubicaciones.setTitulo(null);

        // Create the Ubicaciones, which fails.

        restUbicacionesMockMvc.perform(post("/api/ubicaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ubicaciones)))
            .andExpect(status().isBadRequest());

        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoordenadaXIsRequired() throws Exception {
        int databaseSizeBeforeTest = ubicacionesRepository.findAll().size();
        // set the field null
        ubicaciones.setCoordenadaX(null);

        // Create the Ubicaciones, which fails.

        restUbicacionesMockMvc.perform(post("/api/ubicaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ubicaciones)))
            .andExpect(status().isBadRequest());

        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUbicaciones() throws Exception {
        // Initialize the database
        ubicacionesRepository.saveAndFlush(ubicaciones);

        // Get all the ubicacionesList
        restUbicacionesMockMvc.perform(get("/api/ubicaciones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ubicaciones.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].coordenadaX").value(hasItem(DEFAULT_COORDENADA_X.intValue())))
            .andExpect(jsonPath("$.[*].coordenadaY").value(hasItem(DEFAULT_COORDENADA_Y.intValue())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.doubleValue())))
            .andExpect(jsonPath("$.[*].qr").value(hasItem(DEFAULT_QR)));
    }
    
    @Test
    @Transactional
    public void getUbicaciones() throws Exception {
        // Initialize the database
        ubicacionesRepository.saveAndFlush(ubicaciones);

        // Get the ubicaciones
        restUbicacionesMockMvc.perform(get("/api/ubicaciones/{id}", ubicaciones.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ubicaciones.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.coordenadaX").value(DEFAULT_COORDENADA_X.intValue()))
            .andExpect(jsonPath("$.coordenadaY").value(DEFAULT_COORDENADA_Y.intValue()))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION.doubleValue()))
            .andExpect(jsonPath("$.qr").value(DEFAULT_QR));
    }

    @Test
    @Transactional
    public void getNonExistingUbicaciones() throws Exception {
        // Get the ubicaciones
        restUbicacionesMockMvc.perform(get("/api/ubicaciones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUbicaciones() throws Exception {
        // Initialize the database
        ubicacionesRepository.saveAndFlush(ubicaciones);

        int databaseSizeBeforeUpdate = ubicacionesRepository.findAll().size();

        // Update the ubicaciones
        Ubicaciones updatedUbicaciones = ubicacionesRepository.findById(ubicaciones.getId()).get();
        // Disconnect from session so that the updates on updatedUbicaciones are not directly saved in db
        em.detach(updatedUbicaciones);
        updatedUbicaciones
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .coordenadaX(UPDATED_COORDENADA_X)
            .coordenadaY(UPDATED_COORDENADA_Y)
            .duracion(UPDATED_DURACION)
            .qr(UPDATED_QR);

        restUbicacionesMockMvc.perform(put("/api/ubicaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUbicaciones)))
            .andExpect(status().isOk());

        // Validate the Ubicaciones in the database
        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeUpdate);
        Ubicaciones testUbicaciones = ubicacionesList.get(ubicacionesList.size() - 1);
        assertThat(testUbicaciones.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testUbicaciones.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testUbicaciones.getCoordenadaX()).isEqualTo(UPDATED_COORDENADA_X);
        assertThat(testUbicaciones.getCoordenadaY()).isEqualTo(UPDATED_COORDENADA_Y);
        assertThat(testUbicaciones.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testUbicaciones.getQr()).isEqualTo(UPDATED_QR);
    }

    @Test
    @Transactional
    public void updateNonExistingUbicaciones() throws Exception {
        int databaseSizeBeforeUpdate = ubicacionesRepository.findAll().size();

        // Create the Ubicaciones

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUbicacionesMockMvc.perform(put("/api/ubicaciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ubicaciones)))
            .andExpect(status().isBadRequest());

        // Validate the Ubicaciones in the database
        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUbicaciones() throws Exception {
        // Initialize the database
        ubicacionesRepository.saveAndFlush(ubicaciones);

        int databaseSizeBeforeDelete = ubicacionesRepository.findAll().size();

        // Delete the ubicaciones
        restUbicacionesMockMvc.perform(delete("/api/ubicaciones/{id}", ubicaciones.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ubicaciones> ubicacionesList = ubicacionesRepository.findAll();
        assertThat(ubicacionesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
