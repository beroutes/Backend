package com.beroutes.service.impl;

import com.beroutes.service.FollowerService;
import com.beroutes.domain.Follower;
import com.beroutes.repository.FollowerRepository;
import com.beroutes.repository.search.FollowerSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Follower}.
 */
@Service
@Transactional
public class FollowerServiceImpl implements FollowerService {

    private final Logger log = LoggerFactory.getLogger(FollowerServiceImpl.class);

    private final FollowerRepository followerRepository;

    private final FollowerSearchRepository followerSearchRepository;

    public FollowerServiceImpl(FollowerRepository followerRepository, FollowerSearchRepository followerSearchRepository) {
        this.followerRepository = followerRepository;
        this.followerSearchRepository = followerSearchRepository;
    }

    /**
     * Save a follower.
     *
     * @param follower the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Follower save(Follower follower) {
        log.debug("Request to save Follower : {}", follower);
        Follower result = followerRepository.save(follower);
        followerSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the followers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Follower> findAll(Pageable pageable) {
        log.debug("Request to get all Followers");
        return followerRepository.findAll(pageable);
    }

    /**
     * Get one follower by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Follower> findOne(Long id) {
        log.debug("Request to get Follower : {}", id);
        return followerRepository.findById(id);
    }

    /**
     * Delete the follower by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Follower : {}", id);
        followerRepository.deleteById(id);
        followerSearchRepository.deleteById(id);
    }

    /**
     * Search for the follower corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Follower> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Followers for query {}", query);
        return followerSearchRepository.search(queryStringQuery(query), pageable);    }
}
