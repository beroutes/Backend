package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Categorias;
import com.beroutes.app.repository.CategoriasRepository;

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
 * Integration tests for the {@link CategoriasResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CategoriasResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoriasMockMvc;

    private Categorias categorias;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Categorias createEntity(EntityManager em) {
        Categorias categorias = new Categorias()
            .nombre(DEFAULT_NOMBRE);
        return categorias;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Categorias createUpdatedEntity(EntityManager em) {
        Categorias categorias = new Categorias()
            .nombre(UPDATED_NOMBRE);
        return categorias;
    }

    @BeforeEach
    public void initTest() {
        categorias = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorias() throws Exception {
        int databaseSizeBeforeCreate = categoriasRepository.findAll().size();

        // Create the Categorias
        restCategoriasMockMvc.perform(post("/api/categorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorias)))
            .andExpect(status().isCreated());

        // Validate the Categorias in the database
        List<Categorias> categoriasList = categoriasRepository.findAll();
        assertThat(categoriasList).hasSize(databaseSizeBeforeCreate + 1);
        Categorias testCategorias = categoriasList.get(categoriasList.size() - 1);
        assertThat(testCategorias.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createCategoriasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriasRepository.findAll().size();

        // Create the Categorias with an existing ID
        categorias.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriasMockMvc.perform(post("/api/categorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorias)))
            .andExpect(status().isBadRequest());

        // Validate the Categorias in the database
        List<Categorias> categoriasList = categoriasRepository.findAll();
        assertThat(categoriasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoriasRepository.findAll().size();
        // set the field null
        categorias.setNombre(null);

        // Create the Categorias, which fails.

        restCategoriasMockMvc.perform(post("/api/categorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorias)))
            .andExpect(status().isBadRequest());

        List<Categorias> categoriasList = categoriasRepository.findAll();
        assertThat(categoriasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategorias() throws Exception {
        // Initialize the database
        categoriasRepository.saveAndFlush(categorias);

        // Get all the categoriasList
        restCategoriasMockMvc.perform(get("/api/categorias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorias.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getCategorias() throws Exception {
        // Initialize the database
        categoriasRepository.saveAndFlush(categorias);

        // Get the categorias
        restCategoriasMockMvc.perform(get("/api/categorias/{id}", categorias.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorias.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }

    @Test
    @Transactional
    public void getNonExistingCategorias() throws Exception {
        // Get the categorias
        restCategoriasMockMvc.perform(get("/api/categorias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorias() throws Exception {
        // Initialize the database
        categoriasRepository.saveAndFlush(categorias);

        int databaseSizeBeforeUpdate = categoriasRepository.findAll().size();

        // Update the categorias
        Categorias updatedCategorias = categoriasRepository.findById(categorias.getId()).get();
        // Disconnect from session so that the updates on updatedCategorias are not directly saved in db
        em.detach(updatedCategorias);
        updatedCategorias
            .nombre(UPDATED_NOMBRE);

        restCategoriasMockMvc.perform(put("/api/categorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategorias)))
            .andExpect(status().isOk());

        // Validate the Categorias in the database
        List<Categorias> categoriasList = categoriasRepository.findAll();
        assertThat(categoriasList).hasSize(databaseSizeBeforeUpdate);
        Categorias testCategorias = categoriasList.get(categoriasList.size() - 1);
        assertThat(testCategorias.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorias() throws Exception {
        int databaseSizeBeforeUpdate = categoriasRepository.findAll().size();

        // Create the Categorias

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoriasMockMvc.perform(put("/api/categorias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorias)))
            .andExpect(status().isBadRequest());

        // Validate the Categorias in the database
        List<Categorias> categoriasList = categoriasRepository.findAll();
        assertThat(categoriasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorias() throws Exception {
        // Initialize the database
        categoriasRepository.saveAndFlush(categorias);

        int databaseSizeBeforeDelete = categoriasRepository.findAll().size();

        // Delete the categorias
        restCategoriasMockMvc.perform(delete("/api/categorias/{id}", categorias.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Categorias> categoriasList = categoriasRepository.findAll();
        assertThat(categoriasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
