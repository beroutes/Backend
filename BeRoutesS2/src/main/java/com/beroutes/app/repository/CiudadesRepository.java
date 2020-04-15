package com.beroutes.app.repository;

import com.beroutes.app.domain.Ciudades;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ciudades entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CiudadesRepository extends JpaRepository<Ciudades, Long> {
}
