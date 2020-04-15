package com.beroutes.app.web.rest;

import com.beroutes.app.domain.Ubicaciones;
import com.beroutes.app.repository.UbicacionesRepository;
import com.beroutes.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.beroutes.app.domain.Ubicaciones}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UbicacionesResource {

    private final Logger log = LoggerFactory.getLogger(UbicacionesResource.class);

    private static final String ENTITY_NAME = "ubicaciones";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UbicacionesRepository ubicacionesRepository;

    public UbicacionesResource(UbicacionesRepository ubicacionesRepository) {
        this.ubicacionesRepository = ubicacionesRepository;
    }

    /**
     * {@code POST  /ubicaciones} : Create a new ubicaciones.
     *
     * @param ubicaciones the ubicaciones to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ubicaciones, or with status {@code 400 (Bad Request)} if the ubicaciones has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ubicaciones")
    public ResponseEntity<Ubicaciones> createUbicaciones(@Valid @RequestBody Ubicaciones ubicaciones) throws URISyntaxException {
        log.debug("REST request to save Ubicaciones : {}", ubicaciones);
        if (ubicaciones.getId() != null) {
            throw new BadRequestAlertException("A new ubicaciones cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ubicaciones result = ubicacionesRepository.save(ubicaciones);
        return ResponseEntity.created(new URI("/api/ubicaciones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ubicaciones} : Updates an existing ubicaciones.
     *
     * @param ubicaciones the ubicaciones to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ubicaciones,
     * or with status {@code 400 (Bad Request)} if the ubicaciones is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ubicaciones couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ubicaciones")
    public ResponseEntity<Ubicaciones> updateUbicaciones(@Valid @RequestBody Ubicaciones ubicaciones) throws URISyntaxException {
        log.debug("REST request to update Ubicaciones : {}", ubicaciones);
        if (ubicaciones.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ubicaciones result = ubicacionesRepository.save(ubicaciones);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ubicaciones.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ubicaciones} : get all the ubicaciones.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ubicaciones in body.
     */
    @GetMapping("/ubicaciones")
    public ResponseEntity<List<Ubicaciones>> getAllUbicaciones(Pageable pageable) {
        log.debug("REST request to get a page of Ubicaciones");
        Page<Ubicaciones> page = ubicacionesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ubicaciones/:id} : get the "id" ubicaciones.
     *
     * @param id the id of the ubicaciones to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ubicaciones, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ubicaciones/{id}")
    public ResponseEntity<Ubicaciones> getUbicaciones(@PathVariable Long id) {
        log.debug("REST request to get Ubicaciones : {}", id);
        Optional<Ubicaciones> ubicaciones = ubicacionesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ubicaciones);
    }

    /**
     * {@code DELETE  /ubicaciones/:id} : delete the "id" ubicaciones.
     *
     * @param id the id of the ubicaciones to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ubicaciones/{id}")
    public ResponseEntity<Void> deleteUbicaciones(@PathVariable Long id) {
        log.debug("REST request to delete Ubicaciones : {}", id);
        ubicacionesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
