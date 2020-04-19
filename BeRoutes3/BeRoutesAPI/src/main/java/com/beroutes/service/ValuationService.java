package com.beroutes.service;

import com.beroutes.domain.Valuation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Valuation}.
 */
public interface ValuationService {

    /**
     * Save a valuation.
     *
     * @param valuation the entity to save.
     * @return the persisted entity.
     */
    Valuation save(Valuation valuation);

    /**
     * Get all the valuations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Valuation> findAll(Pageable pageable);

    /**
     * Get the "id" valuation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Valuation> findOne(Long id);

    /**
     * Delete the "id" valuation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the valuation corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Valuation> search(String query, Pageable pageable);
}
