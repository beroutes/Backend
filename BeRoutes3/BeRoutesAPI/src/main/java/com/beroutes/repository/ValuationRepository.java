package com.beroutes.repository;

import com.beroutes.domain.Valuation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Valuation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValuationRepository extends JpaRepository<Valuation, Long> {
}
