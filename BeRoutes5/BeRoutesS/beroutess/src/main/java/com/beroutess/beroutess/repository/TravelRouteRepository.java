package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.TravelRoute;

@Service
public interface TravelRouteRepository extends JpaRepository<TravelRoute, Long> {

}
