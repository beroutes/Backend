package com.beroutes.app.web.rest;

import com.beroutes.app.BeRoutesApp;
import com.beroutes.app.domain.Usuarios;
import com.beroutes.app.repository.UsuariosRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UsuariosResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UsuariosResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOS = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_REGISTRO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_REGISTRO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CIUDAD = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD = "BBBBBBBBBB";

    private static final String DEFAULT_URL_FOTO_PERFIL = "AAAAAAAAAA";
    private static final String UPDATED_URL_FOTO_PERFIL = "BBBBBBBBBB";

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsuariosMockMvc;

    private Usuarios usuarios;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usuarios createEntity(EntityManager em) {
        Usuarios usuarios = new Usuarios()
            .nombre(DEFAULT_NOMBRE)
            .apellidos(DEFAULT_APELLIDOS)
            .email(DEFAULT_EMAIL)
            .usuario(DEFAULT_USUARIO)
            .password(DEFAULT_PASSWORD)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO)
            .ciudad(DEFAULT_CIUDAD)
            .urlFotoPerfil(DEFAULT_URL_FOTO_PERFIL);
        return usuarios;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usuarios createUpdatedEntity(EntityManager em) {
        Usuarios usuarios = new Usuarios()
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .email(UPDATED_EMAIL)
            .usuario(UPDATED_USUARIO)
            .password(UPDATED_PASSWORD)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .ciudad(UPDATED_CIUDAD)
            .urlFotoPerfil(UPDATED_URL_FOTO_PERFIL);
        return usuarios;
    }

    @BeforeEach
    public void initTest() {
        usuarios = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuarios() throws Exception {
        int databaseSizeBeforeCreate = usuariosRepository.findAll().size();

        // Create the Usuarios
        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isCreated());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeCreate + 1);
        Usuarios testUsuarios = usuariosList.get(usuariosList.size() - 1);
        assertThat(testUsuarios.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testUsuarios.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testUsuarios.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUsuarios.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testUsuarios.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUsuarios.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testUsuarios.getCiudad()).isEqualTo(DEFAULT_CIUDAD);
        assertThat(testUsuarios.getUrlFotoPerfil()).isEqualTo(DEFAULT_URL_FOTO_PERFIL);
    }

    @Test
    @Transactional
    public void createUsuariosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuariosRepository.findAll().size();

        // Create the Usuarios with an existing ID
        usuarios.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setNombre(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApellidosIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setApellidos(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setEmail(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setUsuario(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setPassword(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaRegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setFechaRegistro(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCiudadIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setCiudad(null);

        // Create the Usuarios, which fails.

        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        // Get all the usuariosList
        restUsuariosMockMvc.perform(get("/api/usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarios.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellidos").value(hasItem(DEFAULT_APELLIDOS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].ciudad").value(hasItem(DEFAULT_CIUDAD)))
            .andExpect(jsonPath("$.[*].urlFotoPerfil").value(hasItem(DEFAULT_URL_FOTO_PERFIL)));
    }
    
    @Test
    @Transactional
    public void getUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        // Get the usuarios
        restUsuariosMockMvc.perform(get("/api/usuarios/{id}", usuarios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usuarios.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellidos").value(DEFAULT_APELLIDOS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()))
            .andExpect(jsonPath("$.ciudad").value(DEFAULT_CIUDAD))
            .andExpect(jsonPath("$.urlFotoPerfil").value(DEFAULT_URL_FOTO_PERFIL));
    }

    @Test
    @Transactional
    public void getNonExistingUsuarios() throws Exception {
        // Get the usuarios
        restUsuariosMockMvc.perform(get("/api/usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        int databaseSizeBeforeUpdate = usuariosRepository.findAll().size();

        // Update the usuarios
        Usuarios updatedUsuarios = usuariosRepository.findById(usuarios.getId()).get();
        // Disconnect from session so that the updates on updatedUsuarios are not directly saved in db
        em.detach(updatedUsuarios);
        updatedUsuarios
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .email(UPDATED_EMAIL)
            .usuario(UPDATED_USUARIO)
            .password(UPDATED_PASSWORD)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .ciudad(UPDATED_CIUDAD)
            .urlFotoPerfil(UPDATED_URL_FOTO_PERFIL);

        restUsuariosMockMvc.perform(put("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsuarios)))
            .andExpect(status().isOk());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeUpdate);
        Usuarios testUsuarios = usuariosList.get(usuariosList.size() - 1);
        assertThat(testUsuarios.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testUsuarios.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testUsuarios.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUsuarios.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testUsuarios.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUsuarios.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testUsuarios.getCiudad()).isEqualTo(UPDATED_CIUDAD);
        assertThat(testUsuarios.getUrlFotoPerfil()).isEqualTo(UPDATED_URL_FOTO_PERFIL);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuarios() throws Exception {
        int databaseSizeBeforeUpdate = usuariosRepository.findAll().size();

        // Create the Usuarios

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuariosMockMvc.perform(put("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        int databaseSizeBeforeDelete = usuariosRepository.findAll().size();

        // Delete the usuarios
        restUsuariosMockMvc.perform(delete("/api/usuarios/{id}", usuarios.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
