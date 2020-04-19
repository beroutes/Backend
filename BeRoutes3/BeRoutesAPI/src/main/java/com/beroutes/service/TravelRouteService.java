package com.beroutes.service;

import com.beroutes.domain.TravelRoute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TravelRoute}.
 */
public interface TravelRouteService {

    /**
     * Save a travelRoute.
     *
     * @param travelRoute the entity to save.
     * @return the persisted entity.
     */
    TravelRoute save(TravelRoute travelRoute);

    /**
     * Get all the travelRoutes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TravelRoute> findAll(Pageable pageable);

    /**
     * Get the "id" travelRoute.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TravelRoute> findOne(Long id);

    /**
     * Delete the "id" travelRoute.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the travelRoute corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TravelRoute> search(String query, Pageable pageable);
}
