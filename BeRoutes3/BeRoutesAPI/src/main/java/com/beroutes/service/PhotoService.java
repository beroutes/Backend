package com.beroutes.service;

import com.beroutes.domain.Photo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Photo}.
 */
public interface PhotoService {

    /**
     * Save a photo.
     *
     * @param photo the entity to save.
     * @return the persisted entity.
     */
    Photo save(Photo photo);

    /**
     * Get all the photos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Photo> findAll(Pageable pageable);
    /**
     * Get all the PhotoDTO where Location is {@code null}.
     *
     * @return the list of entities.
     */
    List<Photo> findAllWhereLocationIsNull();
    /**
     * Get all the PhotoDTO where UserProfile is {@code null}.
     *
     * @return the list of entities.
     */
    List<Photo> findAllWhereUserProfileIsNull();

    /**
     * Get the "id" photo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Photo> findOne(Long id);

    /**
     * Delete the "id" photo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the photo corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Photo> search(String query, Pageable pageable);
}
