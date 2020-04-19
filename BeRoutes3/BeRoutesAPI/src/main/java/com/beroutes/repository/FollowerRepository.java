package com.beroutes.repository;

import com.beroutes.domain.Follower;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Follower entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
