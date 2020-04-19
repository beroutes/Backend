package com.beroutes.service.impl;

import com.beroutes.service.PhotoService;
import com.beroutes.domain.Photo;
import com.beroutes.repository.PhotoRepository;
import com.beroutes.repository.search.PhotoSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Photo}.
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);

    private final PhotoRepository photoRepository;

    private final PhotoSearchRepository photoSearchRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, PhotoSearchRepository photoSearchRepository) {
        this.photoRepository = photoRepository;
        this.photoSearchRepository = photoSearchRepository;
    }

    /**
     * Save a photo.
     *
     * @param photo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Photo save(Photo photo) {
        log.debug("Request to save Photo : {}", photo);
        Photo result = photoRepository.save(photo);
        photoSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the photos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Photo> findAll(Pageable pageable) {
        log.debug("Request to get all Photos");
        return photoRepository.findAll(pageable);
    }


    /**
     *  Get all the photos where Location is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<Photo> findAllWhereLocationIsNull() {
        log.debug("Request to get all photos where Location is null");
        return StreamSupport
            .stream(photoRepository.findAll().spliterator(), false)
            .filter(photo -> photo.getLocation() == null)
            .collect(Collectors.toList());
    }


    /**
     *  Get all the photos where UserProfile is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<Photo> findAllWhereUserProfileIsNull() {
        log.debug("Request to get all photos where UserProfile is null");
        return StreamSupport
            .stream(photoRepository.findAll().spliterator(), false)
            .filter(photo -> photo.getUserProfile() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one photo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Photo> findOne(Long id) {
        log.debug("Request to get Photo : {}", id);
        return photoRepository.findById(id);
    }

    /**
     * Delete the photo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Photo : {}", id);
        photoRepository.deleteById(id);
        photoSearchRepository.deleteById(id);
    }

    /**
     * Search for the photo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Photo> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Photos for query {}", query);
        return photoSearchRepository.search(queryStringQuery(query), pageable);    }
}
