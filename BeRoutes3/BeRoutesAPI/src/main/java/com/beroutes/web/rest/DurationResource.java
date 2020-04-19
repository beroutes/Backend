package com.beroutes.web.rest;

import com.beroutes.domain.Duration;
import com.beroutes.service.DurationService;
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
 * REST controller for managing {@link com.beroutes.domain.Duration}.
 */
@RestController
@RequestMapping("/api")
public class DurationResource {

    private final Logger log = LoggerFactory.getLogger(DurationResource.class);

    private static final String ENTITY_NAME = "duration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DurationService durationService;

    public DurationResource(DurationService durationService) {
        this.durationService = durationService;
    }

    /**
     * {@code POST  /durations} : Create a new duration.
     *
     * @param duration the duration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duration, or with status {@code 400 (Bad Request)} if the duration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/durations")
    public ResponseEntity<Duration> createDuration(@RequestBody Duration duration) throws URISyntaxException {
        log.debug("REST request to save Duration : {}", duration);
        if (duration.getId() != null) {
            throw new BadRequestAlertException("A new duration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Duration result = durationService.save(duration);
        return ResponseEntity.created(new URI("/api/durations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /durations} : Updates an existing duration.
     *
     * @param duration the duration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duration,
     * or with status {@code 400 (Bad Request)} if the duration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/durations")
    public ResponseEntity<Duration> updateDuration(@RequestBody Duration duration) throws URISyntaxException {
        log.debug("REST request to update Duration : {}", duration);
        if (duration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Duration result = durationService.save(duration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, duration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /durations} : get all the durations.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of durations in body.
     */
    @GetMapping("/durations")
    public ResponseEntity<List<Duration>> getAllDurations(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("travelroute-is-null".equals(filter)) {
            log.debug("REST request to get all Durations where travelRoute is null");
            return new ResponseEntity<>(durationService.findAllWhereTravelRouteIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Durations");
        Page<Duration> page = durationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /durations/:id} : get the "id" duration.
     *
     * @param id the id of the duration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/durations/{id}")
    public ResponseEntity<Duration> getDuration(@PathVariable Long id) {
        log.debug("REST request to get Duration : {}", id);
        Optional<Duration> duration = durationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duration);
    }

    /**
     * {@code DELETE  /durations/:id} : delete the "id" duration.
     *
     * @param id the id of the duration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/durations/{id}")
    public ResponseEntity<Void> deleteDuration(@PathVariable Long id) {
        log.debug("REST request to delete Duration : {}", id);
        durationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/durations?query=:query} : search for the duration corresponding
     * to the query.
     *
     * @param query the query of the duration search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/durations")
    public ResponseEntity<List<Duration>> searchDurations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Durations for query {}", query);
        Page<Duration> page = durationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
