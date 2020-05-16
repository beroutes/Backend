package com.beroutes.app.repository;

import com.beroutes.app.domain.Paises;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Paises entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaisesRepository extends JpaRepository<Paises, Long> {
}
