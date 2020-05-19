package com.beroutess.beroutess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.Location;
import com.beroutess.beroutess.domain.TravelRoute;

@Service
public interface TravelRouteRepository extends JpaRepository<TravelRoute, Long> {
	//List<TravelRoute>findByUserProfile_favorite(Integer id);
}
