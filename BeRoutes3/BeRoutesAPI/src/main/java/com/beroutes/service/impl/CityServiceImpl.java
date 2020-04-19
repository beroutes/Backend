package com.beroutes.service.impl;

import com.beroutes.service.CityService;
import com.beroutes.domain.City;
import com.beroutes.repository.CityRepository;
import com.beroutes.repository.search.CitySearchRepository;
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
 * Service Implementation for managing {@link City}.
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    private final CitySearchRepository citySearchRepository;

    public CityServiceImpl(CityRepository cityRepository, CitySearchRepository citySearchRepository) {
        this.cityRepository = cityRepository;
        this.citySearchRepository = citySearchRepository;
    }

    /**
     * Save a city.
     *
     * @param city the entity to save.
     * @return the persisted entity.
     */
    @Override
    public City save(City city) {
        log.debug("Request to save City : {}", city);
        City result = cityRepository.save(city);
        citySearchRepository.save(result);
        return result;
    }

    /**
     * Get all the cities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<City> findAll(Pageable pageable) {
        log.debug("Request to get all Cities");
        return cityRepository.findAll(pageable);
    }


    /**
     *  Get all the cities where Country is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<City> findAllWhereCountryIsNull() {
        log.debug("Request to get all cities where Country is null");
        return StreamSupport
            .stream(cityRepository.findAll().spliterator(), false)
            .filter(city -> city.getCountry() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one city by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<City> findOne(Long id) {
        log.debug("Request to get City : {}", id);
        return cityRepository.findById(id);
    }

    /**
     * Delete the city by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        cityRepository.deleteById(id);
        citySearchRepository.deleteById(id);
    }

    /**
     * Search for the city corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<City> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Cities for query {}", query);
        return citySearchRepository.search(queryStringQuery(query), pageable);    }
}
