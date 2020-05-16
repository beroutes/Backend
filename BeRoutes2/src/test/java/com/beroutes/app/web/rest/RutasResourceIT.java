package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Rutas;
import com.beroutes.app.repository.RutasRepository;

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
 * Integration tests for the {@link RutasResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RutasResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final Double DEFAULT_DURACION = 1D;
    private static final Double UPDATED_DURACION = 2D;

    private static final String DEFAULT_TEMPORADA = "AAAAAAAAAA";
    private static final String UPDATED_TEMPORADA = "BBBBBBBBBB";

    private static final Double DEFAULT_PRESUPUESTO = 1D;
    private static final Double UPDATED_PRESUPUESTO = 2D;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private RutasRepository rutasRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRutasMockMvc;

    private Rutas rutas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rutas createEntity(EntityManager em) {
        Rutas rutas = new Rutas()
            .titulo(DEFAULT_TITULO)
            .duracion(DEFAULT_DURACION)
            .temporada(DEFAULT_TEMPORADA)
            .presupuesto(DEFAULT_PRESUPUESTO)
            .descripcion(DEFAULT_DESCRIPCION);
        return rutas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rutas createUpdatedEntity(EntityManager em) {
        Rutas rutas = new Rutas()
            .titulo(UPDATED_TITULO)
            .duracion(UPDATED_DURACION)
            .temporada(UPDATED_TEMPORADA)
            .presupuesto(UPDATED_PRESUPUESTO)
            .descripcion(UPDATED_DESCRIPCION);
        return rutas;
    }

    @BeforeEach
    public void initTest() {
        rutas = createEntity(em);
    }

    @Test
    @Transactional
    public void createRutas() throws Exception {
        int databaseSizeBeforeCreate = rutasRepository.findAll().size();

        // Create the Rutas
        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isCreated());

        // Validate the Rutas in the database
        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeCreate + 1);
        Rutas testRutas = rutasList.get(rutasList.size() - 1);
        assertThat(testRutas.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testRutas.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testRutas.getTemporada()).isEqualTo(DEFAULT_TEMPORADA);
        assertThat(testRutas.getPresupuesto()).isEqualTo(DEFAULT_PRESUPUESTO);
        assertThat(testRutas.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createRutasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rutasRepository.findAll().size();

        // Create the Rutas with an existing ID
        rutas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        // Validate the Rutas in the database
        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = rutasRepository.findAll().size();
        // set the field null
        rutas.setTitulo(null);

        // Create the Rutas, which fails.

        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDuracionIsRequired() throws Exception {
        int databaseSizeBeforeTest = rutasRepository.findAll().size();
        // set the field null
        rutas.setDuracion(null);

        // Create the Rutas, which fails.

        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemporadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = rutasRepository.findAll().size();
        // set the field null
        rutas.setTemporada(null);

        // Create the Rutas, which fails.

        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPresupuestoIsRequired() throws Exception {
        int databaseSizeBeforeTest = rutasRepository.findAll().size();
        // set the field null
        rutas.setPresupuesto(null);

        // Create the Rutas, which fails.

        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = rutasRepository.findAll().size();
        // set the field null
        rutas.setDescripcion(null);

        // Create the Rutas, which fails.

        restRutasMockMvc.perform(post("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRutas() throws Exception {
        // Initialize the database
        rutasRepository.saveAndFlush(rutas);

        // Get all the rutasList
        restRutasMockMvc.perform(get("/api/rutas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rutas.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.doubleValue())))
            .andExpect(jsonPath("$.[*].temporada").value(hasItem(DEFAULT_TEMPORADA)))
            .andExpect(jsonPath("$.[*].presupuesto").value(hasItem(DEFAULT_PRESUPUESTO.doubleValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getRutas() throws Exception {
        // Initialize the database
        rutasRepository.saveAndFlush(rutas);

        // Get the rutas
        restRutasMockMvc.perform(get("/api/rutas/{id}", rutas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rutas.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION.doubleValue()))
            .andExpect(jsonPath("$.temporada").value(DEFAULT_TEMPORADA))
            .andExpect(jsonPath("$.presupuesto").value(DEFAULT_PRESUPUESTO.doubleValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingRutas() throws Exception {
        // Get the rutas
        restRutasMockMvc.perform(get("/api/rutas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRutas() throws Exception {
        // Initialize the database
        rutasRepository.saveAndFlush(rutas);

        int databaseSizeBeforeUpdate = rutasRepository.findAll().size();

        // Update the rutas
        Rutas updatedRutas = rutasRepository.findById(rutas.getId()).get();
        // Disconnect from session so that the updates on updatedRutas are not directly saved in db
        em.detach(updatedRutas);
        updatedRutas
            .titulo(UPDATED_TITULO)
            .duracion(UPDATED_DURACION)
            .temporada(UPDATED_TEMPORADA)
            .presupuesto(UPDATED_PRESUPUESTO)
            .descripcion(UPDATED_DESCRIPCION);

        restRutasMockMvc.perform(put("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRutas)))
            .andExpect(status().isOk());

        // Validate the Rutas in the database
        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeUpdate);
        Rutas testRutas = rutasList.get(rutasList.size() - 1);
        assertThat(testRutas.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testRutas.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testRutas.getTemporada()).isEqualTo(UPDATED_TEMPORADA);
        assertThat(testRutas.getPresupuesto()).isEqualTo(UPDATED_PRESUPUESTO);
        assertThat(testRutas.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingRutas() throws Exception {
        int databaseSizeBeforeUpdate = rutasRepository.findAll().size();

        // Create the Rutas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRutasMockMvc.perform(put("/api/rutas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rutas)))
            .andExpect(status().isBadRequest());

        // Validate the Rutas in the database
        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRutas() throws Exception {
        // Initialize the database
        rutasRepository.saveAndFlush(rutas);

        int databaseSizeBeforeDelete = rutasRepository.findAll().size();

        // Delete the rutas
        restRutasMockMvc.perform(delete("/api/rutas/{id}", rutas.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rutas> rutasList = rutasRepository.findAll();
        assertThat(rutasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
