package com.beroutes.app.repository;

import com.beroutes.app.domain.Ubicaciones;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ubicaciones entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UbicacionesRepository extends JpaRepository<Ubicaciones, Long> {
}
