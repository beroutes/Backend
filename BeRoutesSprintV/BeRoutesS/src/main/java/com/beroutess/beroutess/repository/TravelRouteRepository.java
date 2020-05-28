package com.beroutess.beroutess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.Location;
import com.beroutess.beroutess.domain.TravelRoute;

@Service
public interface TravelRouteRepository extends JpaRepository<TravelRoute, Long> {
	List<TravelRoute>findByDestinationIgnoreCaseContaining(String destination);
	List<TravelRoute>findByValueAverageGreaterThanEqual(Double value);
	List<TravelRoute>findByFavorites_userProfile_idEquals(Long favoritesId);
	List<TravelRoute>findByUserProfile_idEquals(Long myRoutesId);
}
