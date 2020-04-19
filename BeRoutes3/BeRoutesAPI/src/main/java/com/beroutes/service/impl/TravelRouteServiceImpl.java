package com.beroutes.service.impl;

import com.beroutes.service.TravelRouteService;
import com.beroutes.domain.TravelRoute;
import com.beroutes.repository.TravelRouteRepository;
import com.beroutes.repository.search.TravelRouteSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TravelRoute}.
 */
@Service
@Transactional
public class TravelRouteServiceImpl implements TravelRouteService {

    private final Logger log = LoggerFactory.getLogger(TravelRouteServiceImpl.class);

    private final TravelRouteRepository travelRouteRepository;

    private final TravelRouteSearchRepository travelRouteSearchRepository;

    public TravelRouteServiceImpl(TravelRouteRepository travelRouteRepository, TravelRouteSearchRepository travelRouteSearchRepository) {
        this.travelRouteRepository = travelRouteRepository;
        this.travelRouteSearchRepository = travelRouteSearchRepository;
    }

    /**
     * Save a travelRoute.
     *
     * @param travelRoute the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TravelRoute save(TravelRoute travelRoute) {
        log.debug("Request to save TravelRoute : {}", travelRoute);
        TravelRoute result = travelRouteRepository.save(travelRoute);
        travelRouteSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the travelRoutes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TravelRoute> findAll(Pageable pageable) {
        log.debug("Request to get all TravelRoutes");
        return travelRouteRepository.findAll(pageable);
    }

    /**
     * Get one travelRoute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TravelRoute> findOne(Long id) {
        log.debug("Request to get TravelRoute : {}", id);
        return travelRouteRepository.findById(id);
    }

    /**
     * Delete the travelRoute by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TravelRoute : {}", id);
        travelRouteRepository.deleteById(id);
        travelRouteSearchRepository.deleteById(id);
    }

    /**
     * Search for the travelRoute corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TravelRoute> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TravelRoutes for query {}", query);
        return travelRouteSearchRepository.search(queryStringQuery(query), pageable);    }
}
