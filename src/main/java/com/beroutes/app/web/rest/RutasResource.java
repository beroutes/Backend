package com.beroutes.app.web.rest;

import com.beroutes.app.domain.Rutas;
import com.beroutes.app.repository.RutasRepository;
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
 * REST controller for managing {@link com.beroutes.app.domain.Rutas}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RutasResource {

    private final Logger log = LoggerFactory.getLogger(RutasResource.class);

    private static final String ENTITY_NAME = "rutas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RutasRepository rutasRepository;

    public RutasResource(RutasRepository rutasRepository) {
        this.rutasRepository = rutasRepository;
    }

    /**
     * {@code POST  /rutas} : Create a new rutas.
     *
     * @param rutas the rutas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rutas, or with status {@code 400 (Bad Request)} if the rutas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rutas")
    public ResponseEntity<Rutas> createRutas(@Valid @RequestBody Rutas rutas) throws URISyntaxException {
        log.debug("REST request to save Rutas : {}", rutas);
        if (rutas.getId() != null) {
            throw new BadRequestAlertException("A new rutas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Rutas result = rutasRepository.save(rutas);
        return ResponseEntity.created(new URI("/api/rutas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rutas} : Updates an existing rutas.
     *
     * @param rutas the rutas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rutas,
     * or with status {@code 400 (Bad Request)} if the rutas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rutas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rutas")
    public ResponseEntity<Rutas> updateRutas(@Valid @RequestBody Rutas rutas) throws URISyntaxException {
        log.debug("REST request to update Rutas : {}", rutas);
        if (rutas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Rutas result = rutasRepository.save(rutas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rutas.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rutas} : get all the rutas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rutas in body.
     */
    @GetMapping("/rutas")
    public ResponseEntity<List<Rutas>> getAllRutas(Pageable pageable) {
        log.debug("REST request to get a page of Rutas");
        Page<Rutas> page = rutasRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rutas/:id} : get the "id" rutas.
     *
     * @param id the id of the rutas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rutas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rutas/{id}")
    public ResponseEntity<Rutas> getRutas(@PathVariable Long id) {
        log.debug("REST request to get Rutas : {}", id);
        Optional<Rutas> rutas = rutasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rutas);
    }

    /**
     * {@code DELETE  /rutas/:id} : delete the "id" rutas.
     *
     * @param id the id of the rutas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rutas/{id}")
    public ResponseEntity<Void> deleteRutas(@PathVariable Long id) {
        log.debug("REST request to delete Rutas : {}", id);
        rutasRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
