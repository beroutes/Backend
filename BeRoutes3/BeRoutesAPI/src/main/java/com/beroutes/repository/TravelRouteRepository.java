package com.beroutes.repository;

import com.beroutes.domain.TravelRoute;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TravelRoute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelRouteRepository extends JpaRepository<TravelRoute, Long> {
}
