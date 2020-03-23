package com.beroutes.app.web.rest;

import com.beroutes.app.domain.Valoraciones;
import com.beroutes.app.repository.ValoracionesRepository;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.beroutes.app.domain.Valoraciones}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ValoracionesResource {

    private final Logger log = LoggerFactory.getLogger(ValoracionesResource.class);

    private static final String ENTITY_NAME = "valoraciones";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValoracionesRepository valoracionesRepository;

    public ValoracionesResource(ValoracionesRepository valoracionesRepository) {
        this.valoracionesRepository = valoracionesRepository;
    }

    /**
     * {@code POST  /valoraciones} : Create a new valoraciones.
     *
     * @param valoraciones the valoraciones to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new valoraciones, or with status {@code 400 (Bad Request)} if the valoraciones has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/valoraciones")
    public ResponseEntity<Valoraciones> createValoraciones(@RequestBody Valoraciones valoraciones) throws URISyntaxException {
        log.debug("REST request to save Valoraciones : {}", valoraciones);
        if (valoraciones.getId() != null) {
            throw new BadRequestAlertException("A new valoraciones cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Valoraciones result = valoracionesRepository.save(valoraciones);
        return ResponseEntity.created(new URI("/api/valoraciones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /valoraciones} : Updates an existing valoraciones.
     *
     * @param valoraciones the valoraciones to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated valoraciones,
     * or with status {@code 400 (Bad Request)} if the valoraciones is not valid,
     * or with status {@code 500 (Internal Server Error)} if the valoraciones couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/valoraciones")
    public ResponseEntity<Valoraciones> updateValoraciones(@RequestBody Valoraciones valoraciones) throws URISyntaxException {
        log.debug("REST request to update Valoraciones : {}", valoraciones);
        if (valoraciones.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Valoraciones result = valoracionesRepository.save(valoraciones);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, valoraciones.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /valoraciones} : get all the valoraciones.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of valoraciones in body.
     */
    @GetMapping("/valoraciones")
    public ResponseEntity<List<Valoraciones>> getAllValoraciones(Pageable pageable) {
        log.debug("REST request to get a page of Valoraciones");
        Page<Valoraciones> page = valoracionesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /valoraciones/:id} : get the "id" valoraciones.
     *
     * @param id the id of the valoraciones to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the valoraciones, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/valoraciones/{id}")
    public ResponseEntity<Valoraciones> getValoraciones(@PathVariable Long id) {
        log.debug("REST request to get Valoraciones : {}", id);
        Optional<Valoraciones> valoraciones = valoracionesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(valoraciones);
    }

    /**
     * {@code DELETE  /valoraciones/:id} : delete the "id" valoraciones.
     *
     * @param id the id of the valoraciones to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/valoraciones/{id}")
    public ResponseEntity<Void> deleteValoraciones(@PathVariable Long id) {
        log.debug("REST request to delete Valoraciones : {}", id);
        valoracionesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
