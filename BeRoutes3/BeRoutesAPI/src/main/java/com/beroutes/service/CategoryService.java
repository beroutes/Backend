package com.beroutes.service;

import com.beroutes.domain.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Category}.
 */
public interface CategoryService {

    /**
     * Save a category.
     *
     * @param category the entity to save.
     * @return the persisted entity.
     */
    Category save(Category category);

    /**
     * Get all the categories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Category> findAll(Pageable pageable);
    /**
     * Get all the CategoryDTO where TravelRoute is {@code null}.
     *
     * @return the list of entities.
     */
    List<Category> findAllWhereTravelRouteIsNull();

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Category> findOne(Long id);

    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the category corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Category> search(String query, Pageable pageable);
}
