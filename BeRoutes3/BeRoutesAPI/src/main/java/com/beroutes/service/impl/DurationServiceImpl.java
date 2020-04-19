package com.beroutes.service.impl;

import com.beroutes.service.DurationService;
import com.beroutes.domain.Duration;
import com.beroutes.repository.DurationRepository;
import com.beroutes.repository.search.DurationSearchRepository;
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
 * Service Implementation for managing {@link Duration}.
 */
@Service
@Transactional
public class DurationServiceImpl implements DurationService {

    private final Logger log = LoggerFactory.getLogger(DurationServiceImpl.class);

    private final DurationRepository durationRepository;

    private final DurationSearchRepository durationSearchRepository;

    public DurationServiceImpl(DurationRepository durationRepository, DurationSearchRepository durationSearchRepository) {
        this.durationRepository = durationRepository;
        this.durationSearchRepository = durationSearchRepository;
    }

    /**
     * Save a duration.
     *
     * @param duration the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Duration save(Duration duration) {
        log.debug("Request to save Duration : {}", duration);
        Duration result = durationRepository.save(duration);
        durationSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the durations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Duration> findAll(Pageable pageable) {
        log.debug("Request to get all Durations");
        return durationRepository.findAll(pageable);
    }


    /**
     *  Get all the durations where TravelRoute is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<Duration> findAllWhereTravelRouteIsNull() {
        log.debug("Request to get all durations where TravelRoute is null");
        return StreamSupport
            .stream(durationRepository.findAll().spliterator(), false)
            .filter(duration -> duration.getTravelRoute() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one duration by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Duration> findOne(Long id) {
        log.debug("Request to get Duration : {}", id);
        return durationRepository.findById(id);
    }

    /**
     * Delete the duration by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Duration : {}", id);
        durationRepository.deleteById(id);
        durationSearchRepository.deleteById(id);
    }

    /**
     * Search for the duration corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Duration> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Durations for query {}", query);
        return durationSearchRepository.search(queryStringQuery(query), pageable);    }
}
