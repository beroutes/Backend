package com.beroutes.repository;

import com.beroutes.domain.Duration;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Duration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DurationRepository extends JpaRepository<Duration, Long> {
}
