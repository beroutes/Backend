package com.beroutes.service;

import com.beroutes.domain.Duration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Duration}.
 */
public interface DurationService {

    /**
     * Save a duration.
     *
     * @param duration the entity to save.
     * @return the persisted entity.
     */
    Duration save(Duration duration);

    /**
     * Get all the durations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Duration> findAll(Pageable pageable);
    /**
     * Get all the DurationDTO where TravelRoute is {@code null}.
     *
     * @return the list of entities.
     */
    List<Duration> findAllWhereTravelRouteIsNull();

    /**
     * Get the "id" duration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Duration> findOne(Long id);

    /**
     * Delete the "id" duration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the duration corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Duration> search(String query, Pageable pageable);
}
