package com.beroutes.service.impl;

import com.beroutes.service.UserProfileService;
import com.beroutes.domain.UserProfile;
import com.beroutes.repository.UserProfileRepository;
import com.beroutes.repository.search.UserProfileSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link UserProfile}.
 */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    private final UserProfileRepository userProfileRepository;

    private final UserProfileSearchRepository userProfileSearchRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserProfileSearchRepository userProfileSearchRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileSearchRepository = userProfileSearchRepository;
    }

    /**
     * Save a userProfile.
     *
     * @param userProfile the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserProfile save(UserProfile userProfile) {
        log.debug("Request to save UserProfile : {}", userProfile);
        UserProfile result = userProfileRepository.save(userProfile);
        userProfileSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the userProfiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserProfile> findAll(Pageable pageable) {
        log.debug("Request to get all UserProfiles");
        return userProfileRepository.findAll(pageable);
    }

    /**
     * Get all the userProfiles with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<UserProfile> findAllWithEagerRelationships(Pageable pageable) {
        return userProfileRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one userProfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserProfile> findOne(Long id) {
        log.debug("Request to get UserProfile : {}", id);
        return userProfileRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the userProfile by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserProfile : {}", id);
        userProfileRepository.deleteById(id);
        userProfileSearchRepository.deleteById(id);
    }

    /**
     * Search for the userProfile corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserProfile> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserProfiles for query {}", query);
        return userProfileSearchRepository.search(queryStringQuery(query), pageable);    }
}
