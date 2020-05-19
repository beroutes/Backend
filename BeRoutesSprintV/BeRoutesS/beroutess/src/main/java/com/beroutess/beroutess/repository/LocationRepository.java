package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beroutess.beroutess.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	

}
