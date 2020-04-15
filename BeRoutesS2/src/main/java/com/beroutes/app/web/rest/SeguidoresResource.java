package com.beroutes.app.web.rest;

import com.beroutes.app.domain.Seguidores;
import com.beroutes.app.repository.SeguidoresRepository;
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
 * REST controller for managing {@link com.beroutes.app.domain.Seguidores}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SeguidoresResource {

    private final Logger log = LoggerFactory.getLogger(SeguidoresResource.class);

    private static final String ENTITY_NAME = "seguidores";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SeguidoresRepository seguidoresRepository;

    public SeguidoresResource(SeguidoresRepository seguidoresRepository) {
        this.seguidoresRepository = seguidoresRepository;
    }

    /**
     * {@code POST  /seguidores} : Create a new seguidores.
     *
     * @param seguidores the seguidores to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new seguidores, or with status {@code 400 (Bad Request)} if the seguidores has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/seguidores")
    public ResponseEntity<Seguidores> createSeguidores(@RequestBody Seguidores seguidores) throws URISyntaxException {
        log.debug("REST request to save Seguidores : {}", seguidores);
        if (seguidores.getId() != null) {
            throw new BadRequestAlertException("A new seguidores cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Seguidores result = seguidoresRepository.save(seguidores);
        return ResponseEntity.created(new URI("/api/seguidores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /seguidores} : Updates an existing seguidores.
     *
     * @param seguidores the seguidores to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seguidores,
     * or with status {@code 400 (Bad Request)} if the seguidores is not valid,
     * or with status {@code 500 (Internal Server Error)} if the seguidores couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/seguidores")
    public ResponseEntity<Seguidores> updateSeguidores(@RequestBody Seguidores seguidores) throws URISyntaxException {
        log.debug("REST request to update Seguidores : {}", seguidores);
        if (seguidores.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Seguidores result = seguidoresRepository.save(seguidores);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, seguidores.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /seguidores} : get all the seguidores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of seguidores in body.
     */
    @GetMapping("/seguidores")
    public ResponseEntity<List<Seguidores>> getAllSeguidores(Pageable pageable) {
        log.debug("REST request to get a page of Seguidores");
        Page<Seguidores> page = seguidoresRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /seguidores/:id} : get the "id" seguidores.
     *
     * @param id the id of the seguidores to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the seguidores, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/seguidores/{id}")
    public ResponseEntity<Seguidores> getSeguidores(@PathVariable Long id) {
        log.debug("REST request to get Seguidores : {}", id);
        Optional<Seguidores> seguidores = seguidoresRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(seguidores);
    }

    /**
     * {@code DELETE  /seguidores/:id} : delete the "id" seguidores.
     *
     * @param id the id of the seguidores to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/seguidores/{id}")
    public ResponseEntity<Void> deleteSeguidores(@PathVariable Long id) {
        log.debug("REST request to delete Seguidores : {}", id);
        seguidoresRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
