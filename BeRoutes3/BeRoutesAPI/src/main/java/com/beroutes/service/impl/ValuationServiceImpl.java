package com.beroutes.service.impl;

import com.beroutes.service.ValuationService;
import com.beroutes.domain.Valuation;
import com.beroutes.repository.ValuationRepository;
import com.beroutes.repository.search.ValuationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Valuation}.
 */
@Service
@Transactional
public class ValuationServiceImpl implements ValuationService {

    private final Logger log = LoggerFactory.getLogger(ValuationServiceImpl.class);

    private final ValuationRepository valuationRepository;

    private final ValuationSearchRepository valuationSearchRepository;

    public ValuationServiceImpl(ValuationRepository valuationRepository, ValuationSearchRepository valuationSearchRepository) {
        this.valuationRepository = valuationRepository;
        this.valuationSearchRepository = valuationSearchRepository;
    }

    /**
     * Save a valuation.
     *
     * @param valuation the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Valuation save(Valuation valuation) {
        log.debug("Request to save Valuation : {}", valuation);
        Valuation result = valuationRepository.save(valuation);
        valuationSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the valuations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Valuation> findAll(Pageable pageable) {
        log.debug("Request to get all Valuations");
        return valuationRepository.findAll(pageable);
    }

    /**
     * Get one valuation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Valuation> findOne(Long id) {
        log.debug("Request to get Valuation : {}", id);
        return valuationRepository.findById(id);
    }

    /**
     * Delete the valuation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Valuation : {}", id);
        valuationRepository.deleteById(id);
        valuationSearchRepository.deleteById(id);
    }

    /**
     * Search for the valuation corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Valuation> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Valuations for query {}", query);
        return valuationSearchRepository.search(queryStringQuery(query), pageable);    }
}
