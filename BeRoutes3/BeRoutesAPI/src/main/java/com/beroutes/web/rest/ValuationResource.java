package com.beroutes.web.rest;

import com.beroutes.domain.Valuation;
import com.beroutes.service.ValuationService;
import com.beroutes.web.rest.errors.BadRequestAlertException;

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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.beroutes.domain.Valuation}.
 */
@RestController
@RequestMapping("/api")
public class ValuationResource {

    private final Logger log = LoggerFactory.getLogger(ValuationResource.class);

    private static final String ENTITY_NAME = "valuation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValuationService valuationService;

    public ValuationResource(ValuationService valuationService) {
        this.valuationService = valuationService;
    }

    /**
     * {@code POST  /valuations} : Create a new valuation.
     *
     * @param valuation the valuation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new valuation, or with status {@code 400 (Bad Request)} if the valuation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/valuations")
    public ResponseEntity<Valuation> createValuation(@RequestBody Valuation valuation) throws URISyntaxException {
        log.debug("REST request to save Valuation : {}", valuation);
        if (valuation.getId() != null) {
            throw new BadRequestAlertException("A new valuation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Valuation result = valuationService.save(valuation);
        return ResponseEntity.created(new URI("/api/valuations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /valuations} : Updates an existing valuation.
     *
     * @param valuation the valuation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated valuation,
     * or with status {@code 400 (Bad Request)} if the valuation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the valuation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/valuations")
    public ResponseEntity<Valuation> updateValuation(@RequestBody Valuation valuation) throws URISyntaxException {
        log.debug("REST request to update Valuation : {}", valuation);
        if (valuation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Valuation result = valuationService.save(valuation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, valuation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /valuations} : get all the valuations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of valuations in body.
     */
    @GetMapping("/valuations")
    public ResponseEntity<List<Valuation>> getAllValuations(Pageable pageable) {
        log.debug("REST request to get a page of Valuations");
        Page<Valuation> page = valuationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /valuations/:id} : get the "id" valuation.
     *
     * @param id the id of the valuation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the valuation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/valuations/{id}")
    public ResponseEntity<Valuation> getValuation(@PathVariable Long id) {
        log.debug("REST request to get Valuation : {}", id);
        Optional<Valuation> valuation = valuationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(valuation);
    }

    /**
     * {@code DELETE  /valuations/:id} : delete the "id" valuation.
     *
     * @param id the id of the valuation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/valuations/{id}")
    public ResponseEntity<Void> deleteValuation(@PathVariable Long id) {
        log.debug("REST request to delete Valuation : {}", id);
        valuationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/valuations?query=:query} : search for the valuation corresponding
     * to the query.
     *
     * @param query the query of the valuation search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/valuations")
    public ResponseEntity<List<Valuation>> searchValuations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Valuations for query {}", query);
        Page<Valuation> page = valuationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
