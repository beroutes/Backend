package com.beroutes.app.repository;

import com.beroutes.app.domain.Fotos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fotos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FotosRepository extends JpaRepository<Fotos, Long> {
}
