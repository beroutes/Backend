package com.beroutes.web.rest;

import com.beroutes.domain.TravelRoute;
import com.beroutes.service.TravelRouteService;
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
 * REST controller for managing {@link com.beroutes.domain.TravelRoute}.
 */
@RestController
@RequestMapping("/api")
public class TravelRouteResource {

    private final Logger log = LoggerFactory.getLogger(TravelRouteResource.class);

    private static final String ENTITY_NAME = "travelRoute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelRouteService travelRouteService;

    public TravelRouteResource(TravelRouteService travelRouteService) {
        this.travelRouteService = travelRouteService;
    }

    /**
     * {@code POST  /travel-routes} : Create a new travelRoute.
     *
     * @param travelRoute the travelRoute to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelRoute, or with status {@code 400 (Bad Request)} if the travelRoute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-routes")
    public ResponseEntity<TravelRoute> createTravelRoute(@RequestBody TravelRoute travelRoute) throws URISyntaxException {
        log.debug("REST request to save TravelRoute : {}", travelRoute);
        if (travelRoute.getId() != null) {
            throw new BadRequestAlertException("A new travelRoute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelRoute result = travelRouteService.save(travelRoute);
        return ResponseEntity.created(new URI("/api/travel-routes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-routes} : Updates an existing travelRoute.
     *
     * @param travelRoute the travelRoute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelRoute,
     * or with status {@code 400 (Bad Request)} if the travelRoute is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelRoute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-routes")
    public ResponseEntity<TravelRoute> updateTravelRoute(@RequestBody TravelRoute travelRoute) throws URISyntaxException {
        log.debug("REST request to update TravelRoute : {}", travelRoute);
        if (travelRoute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelRoute result = travelRouteService.save(travelRoute);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelRoute.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-routes} : get all the travelRoutes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelRoutes in body.
     */
    @GetMapping("/travel-routes")
    public ResponseEntity<List<TravelRoute>> getAllTravelRoutes(Pageable pageable) {
        log.debug("REST request to get a page of TravelRoutes");
        Page<TravelRoute> page = travelRouteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-routes/:id} : get the "id" travelRoute.
     *
     * @param id the id of the travelRoute to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelRoute, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-routes/{id}")
    public ResponseEntity<TravelRoute> getTravelRoute(@PathVariable Long id) {
        log.debug("REST request to get TravelRoute : {}", id);
        Optional<TravelRoute> travelRoute = travelRouteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(travelRoute);
    }

    /**
     * {@code DELETE  /travel-routes/:id} : delete the "id" travelRoute.
     *
     * @param id the id of the travelRoute to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-routes/{id}")
    public ResponseEntity<Void> deleteTravelRoute(@PathVariable Long id) {
        log.debug("REST request to delete TravelRoute : {}", id);
        travelRouteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/travel-routes?query=:query} : search for the travelRoute corresponding
     * to the query.
     *
     * @param query the query of the travelRoute search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/travel-routes")
    public ResponseEntity<List<TravelRoute>> searchTravelRoutes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TravelRoutes for query {}", query);
        Page<TravelRoute> page = travelRouteService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
