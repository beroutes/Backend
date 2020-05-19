package com.beroutess.beroutess.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beroutess.beroutess.service.CountryService;
import com.beroutess.beroutess.service.FavoriteService;
import com.beroutess.beroutess.service.LocationService;
import com.beroutess.beroutess.service.PhotoService;
import com.beroutess.beroutess.service.TravelRouteService;
import com.beroutess.beroutess.service.UserProfileService;

@RestController
public class InitController {
	CountryService countryService;
	FavoriteService favoriteService;
	LocationService locationService;
	PhotoService photoService;
	TravelRouteService travelRouteService;
	UserProfileService userProfileService;
	
	public InitController(CountryService countryService, FavoriteService favoriteService,
			LocationService locationService, PhotoService photoService, TravelRouteService travelRouteService,
			UserProfileService userProfileService) {
	
		this.countryService = countryService;
		this.favoriteService = favoriteService;
		this.locationService = locationService;
		this.photoService = photoService;
		this.travelRouteService = travelRouteService;
		this.userProfileService = userProfileService;
	}
	
	@GetMapping(path = "/beroutes/init")
	String initEntities() {
		countryService.initCountries();
		favoriteService.initFavorites();
		locationService.initLocations();
		photoService.initPhotos();
		travelRouteService.initTraveRoute();
		userProfileService.initUserProfiles();
		return "routes initialize";
	}
	
}
