package com.beroutes.service.impl;

import com.beroutes.service.RegionService;
import com.beroutes.domain.Region;
import com.beroutes.repository.RegionRepository;
import com.beroutes.repository.search.RegionSearchRepository;
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
 * Service Implementation for managing {@link Region}.
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

    private final RegionRepository regionRepository;

    private final RegionSearchRepository regionSearchRepository;

    public RegionServiceImpl(RegionRepository regionRepository, RegionSearchRepository regionSearchRepository) {
        this.regionRepository = regionRepository;
        this.regionSearchRepository = regionSearchRepository;
    }

    /**
     * Save a region.
     *
     * @param region the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Region save(Region region) {
        log.debug("Request to save Region : {}", region);
        Region result = regionRepository.save(region);
        regionSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the regions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Region> findAll(Pageable pageable) {
        log.debug("Request to get all Regions");
        return regionRepository.findAll(pageable);
    }


    /**
     *  Get all the regions where Country is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<Region> findAllWhereCountryIsNull() {
        log.debug("Request to get all regions where Country is null");
        return StreamSupport
            .stream(regionRepository.findAll().spliterator(), false)
            .filter(region -> region.getCountry() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one region by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Region> findOne(Long id) {
        log.debug("Request to get Region : {}", id);
        return regionRepository.findById(id);
    }

    /**
     * Delete the region by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Region : {}", id);
        regionRepository.deleteById(id);
        regionSearchRepository.deleteById(id);
    }

    /**
     * Search for the region corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Region> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Regions for query {}", query);
        return regionSearchRepository.search(queryStringQuery(query), pageable);    }
}
