package com.beroutes.app.web.rest;

import com.beroutes.app.domain.Fotos;
import com.beroutes.app.repository.FotosRepository;
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
 * REST controller for managing {@link com.beroutes.app.domain.Fotos}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FotosResource {

    private final Logger log = LoggerFactory.getLogger(FotosResource.class);

    private static final String ENTITY_NAME = "fotos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FotosRepository fotosRepository;

    public FotosResource(FotosRepository fotosRepository) {
        this.fotosRepository = fotosRepository;
    }

    /**
     * {@code POST  /fotos} : Create a new fotos.
     *
     * @param fotos the fotos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fotos, or with status {@code 400 (Bad Request)} if the fotos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fotos")
    public ResponseEntity<Fotos> createFotos(@Valid @RequestBody Fotos fotos) throws URISyntaxException {
        log.debug("REST request to save Fotos : {}", fotos);
        if (fotos.getId() != null) {
            throw new BadRequestAlertException("A new fotos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Fotos result = fotosRepository.save(fotos);
        return ResponseEntity.created(new URI("/api/fotos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fotos} : Updates an existing fotos.
     *
     * @param fotos the fotos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fotos,
     * or with status {@code 400 (Bad Request)} if the fotos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fotos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fotos")
    public ResponseEntity<Fotos> updateFotos(@Valid @RequestBody Fotos fotos) throws URISyntaxException {
        log.debug("REST request to update Fotos : {}", fotos);
        if (fotos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Fotos result = fotosRepository.save(fotos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fotos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fotos} : get all the fotos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fotos in body.
     */
    @GetMapping("/fotos")
    public ResponseEntity<List<Fotos>> getAllFotos(Pageable pageable) {
        log.debug("REST request to get a page of Fotos");
        Page<Fotos> page = fotosRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fotos/:id} : get the "id" fotos.
     *
     * @param id the id of the fotos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fotos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fotos/{id}")
    public ResponseEntity<Fotos> getFotos(@PathVariable Long id) {
        log.debug("REST request to get Fotos : {}", id);
        Optional<Fotos> fotos = fotosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fotos);
    }

    /**
     * {@code DELETE  /fotos/:id} : delete the "id" fotos.
     *
     * @param id the id of the fotos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fotos/{id}")
    public ResponseEntity<Void> deleteFotos(@PathVariable Long id) {
        log.debug("REST request to delete Fotos : {}", id);
        fotosRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
