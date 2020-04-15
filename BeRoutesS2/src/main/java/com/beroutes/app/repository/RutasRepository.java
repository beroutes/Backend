package com.beroutes.app.repository;

import com.beroutes.app.domain.Rutas;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Rutas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RutasRepository extends JpaRepository<Rutas, Long> {
}
