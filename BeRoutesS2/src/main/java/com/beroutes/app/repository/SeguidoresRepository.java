package com.beroutes.app.repository;

import com.beroutes.app.domain.Seguidores;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Seguidores entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeguidoresRepository extends JpaRepository<Seguidores, Long> {
}
