package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beroutess.beroutess.domain.Following;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {

}
