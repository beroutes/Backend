package com.beroutes.service;

import com.beroutes.domain.Follower;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Follower}.
 */
public interface FollowerService {

    /**
     * Save a follower.
     *
     * @param follower the entity to save.
     * @return the persisted entity.
     */
    Follower save(Follower follower);

    /**
     * Get all the followers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Follower> findAll(Pageable pageable);

    /**
     * Get the "id" follower.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Follower> findOne(Long id);

    /**
     * Delete the "id" follower.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the follower corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Follower> search(String query, Pageable pageable);
}
