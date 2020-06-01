package com.beroutess.beroutess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.enumeration.Category;
import com.beroutess.beroutess.domain.enumeration.CategoryTwo;
import com.beroutess.beroutess.domain.enumeration.Continent;
import com.beroutess.beroutess.domain.enumeration.Season;
import com.beroutess.beroutess.domain.TravelRoute;
import com.beroutess.beroutess.repository.TravelRouteRepository;


@Service
public class TravelRouteService {
	
	//Llamamos a la información del repositorio
	TravelRouteRepository travelRouteRepository;
	//Inicializamos con un constructor
	public TravelRouteService(TravelRouteRepository travelRouteRepository) {
	
	
		this.travelRouteRepository = travelRouteRepository;

	}
	
	///Los métodos
	
	public List<TravelRoute>getTravelRoutes(){
		return travelRouteRepository.findAll();
	}
	
	/*
	public TravelRoute getTravelRoute(Long id) {
		return travelRouteRepository.getOne(id);
	}
	*/
	public Optional<TravelRoute> findById(Long id) {
		return travelRouteRepository.findById(id);
	}

	public Long addTravelRoute(TravelRoute travelRoute) {
		TravelRoute travelRouteSaved = travelRouteRepository.save(travelRoute);
		return travelRouteSaved.getId();		
	}
	
	public Long deleteTravelRoute(Long id) {
		travelRouteRepository.delete(travelRouteRepository.getOne(id));
		return travelRouteRepository.count();
	}
	
	public TravelRoute editTravelRoute(Long id, TravelRoute travelRoute) {
		TravelRoute travelRouteSaved = travelRouteRepository.getOne(id);
		travelRouteSaved.setTitleRoute(travelRoute.getTitleRoute());
		travelRouteSaved.setDestination(travelRoute.getDestination());
		travelRouteSaved.setContinent(travelRoute.getContinent());
		travelRouteSaved.setDays(travelRoute.getDays());
		travelRouteSaved.setWeeks(travelRoute.getWeeks());
		travelRouteSaved.setSeason(travelRoute.getSeason());
		travelRouteSaved.setBudget(travelRoute.getBudget());
		travelRouteSaved.setCategory(travelRoute.getCategory());
		travelRouteSaved.setCategoryTwo(travelRoute.getCategoryTwo());
		travelRouteSaved.setValueAverage(travelRoute.getValueAverage());
		travelRouteSaved.setDescriptionRouteSummary(travelRoute.getDescriptionRouteSummary());
		travelRouteSaved.setDescriptionRoute(travelRoute.getDescriptionRoute());
		travelRouteSaved.setSteps(travelRoute.getSteps());
		travelRouteSaved.setSummaryMap(travelRoute.getSummaryMap());
		travelRouteSaved.setCreatedAt(travelRoute.getCreatedAt());
		travelRouteSaved.setUpdatedAt(travelRoute.getUpdatedAt());
		//travelRouteSaved.setQrActivation(travelRoute.getQrActivation());
		
		/*
		travelRouteSaved.setCountry(travelRoute.getCountry());
		*/
		travelRouteSaved.setLocations(travelRoute.getLocations());
		
		travelRouteSaved.setFavorites(travelRoute.getFavorites());
		
		/*
		travelRouteSaved.setPhotos(travelRoute.getPhotos());
		travelRouteSaved.setValuations(travelRoute.getValuations());
		travelRouteSaved.setQrs(travelRoute.getQrs());
		*/
		travelRouteSaved.setUserProfile(travelRoute.getUserProfile());
		
		travelRouteRepository.save(travelRouteSaved);
		return travelRouteSaved;
	}
	
	///Metodos para icializar TravelsRoutes
	
	public List<TravelRoute> initTraveRoute() {
		
		TravelRoute newTravelRoute0 = new TravelRoute();
		newTravelRoute0.setTitleRoute("San Francisco. With my best friend");
		newTravelRoute0.setDestination("San Francisco");
		newTravelRoute0.setContinent(Continent.AMERICANORTH);
		newTravelRoute0.setDays(5);
		newTravelRoute0.setWeeks(0);
		newTravelRoute0.setSeason(Season.AUTUMN);
		newTravelRoute0.setBudget(1000.0);
		newTravelRoute0.setCategory(Category.FRIENDS);
		newTravelRoute0.setCategoryTwo(CategoryTwo.CITY);
		newTravelRoute0.setValueAverage(9.2);
		newTravelRoute0.setDescriptionRouteSummary("Lorem Ipsun istin icole hasta que fisticolico de nimiluti.");
		newTravelRoute0.setDescriptionRoute(" Elementum cubilia parturient sociis. Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\\\\r\\\\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis");
		newTravelRoute0.setSteps(5);
		newTravelRoute0.setSummaryMap("Día 1: Union Square, Fisherman's Wharf y Marina District; Día 2: Twin Peaks, Golden Gate Park, Alamo Square.;Día 3: Sausalito, Presidio Park y Castro; Día 4: Isla Alcatraz, Chinatown y Coit Tower; Día 5: Yerba Buena o Point Reyes.");
		newTravelRoute0.setCreatedAt(null);
		newTravelRoute0.setUpdatedAt(null);
		//newTravelRoute0.setQrActivation(false);
		
		travelRouteRepository.save(newTravelRoute0);
		
		
		TravelRoute newTravelRoute1 = new TravelRoute();
		newTravelRoute1.setTitleRoute("Cappadocia bella");
		newTravelRoute1.setDestination("Turkey");
		newTravelRoute1.setContinent(Continent.EUROPE);
		newTravelRoute1.setDays(14);
		newTravelRoute1.setWeeks(2);
		newTravelRoute1.setSeason(Season.SPRING);
		newTravelRoute1.setBudget(1500.0);
		newTravelRoute1.setCategory(Category.ROMANTIC);
		newTravelRoute1.setCategoryTwo(CategoryTwo.NATURE);
		newTravelRoute1.setValueAverage(9.3);
		newTravelRoute1.setDescriptionRouteSummary("Lorem Ipsun istin icole hasta que fisticolico de nimiluti.");
		newTravelRoute1.setDescriptionRoute(" Elementum cubilia parturient sociis. Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\\\\r\\\\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis");
		newTravelRoute1.setSteps(4);
		newTravelRoute1.setSummaryMap("1:Museo al Aire Libre de Göreme.; 2:Castillo de Uchisar.; 3:Ortahisar.; 4:Ürgup.");
		newTravelRoute1.setCreatedAt(null);
		newTravelRoute1.setUpdatedAt(null);
		//newTravelRoute1.setQrActivation(false);
		
		travelRouteRepository.save(newTravelRoute1);
		
		TravelRoute newTravelRoute2 = new TravelRoute();
		newTravelRoute2.setTitleRoute("Greenland gift to the senses");
		newTravelRoute2.setDestination("Greenland");
		newTravelRoute2.setContinent(Continent.AMERICANORTH);
		newTravelRoute2.setDays(15);
		newTravelRoute2.setWeeks(2);
		newTravelRoute2.setSeason(Season.SPRING);
		newTravelRoute2.setBudget(2500.0);
		newTravelRoute2.setCategory(Category.ROMANTIC);
		newTravelRoute2.setCategoryTwo(CategoryTwo.NATURE);
		newTravelRoute2.setValueAverage(9.4);
		newTravelRoute2.setDescriptionRouteSummary("Lorem Ipsun istin icole hasta que fisticolico de nimiluti.");
		newTravelRoute2.setDescriptionRoute(" Elementum cubilia parturient sociis. Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\\\\r\\\\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis");
		newTravelRoute2.setSteps(9);
		newTravelRoute2.setSummaryMap("1 El valle glaciar de las Mil Flores; 2 Glaciar Kiattut; 3 Mercado de pescadores en Narsaq; 4 Glaciar Qaleraliq; 5 Lago Tasersuatsiaq; 6 Mirador al Inlandis; 7 Igaliku; 8 Glaciar Qooroq; 9 Narsarsuaq Museum");
		newTravelRoute2.setCreatedAt(null);
		newTravelRoute2.setUpdatedAt(null);
		//newTravelRoute2.setQrActivation(false);
		
		travelRouteRepository.save(newTravelRoute2);
		
		TravelRoute newTravelRoute3 = new TravelRoute();
		newTravelRoute3.setTitleRoute("Bike trip in morocco");
		newTravelRoute3.setDestination("Morocco");
		newTravelRoute3.setContinent(Continent.AFRICA);
		newTravelRoute3.setDays(7);
		newTravelRoute3.setWeeks(1);
		newTravelRoute3.setSeason(Season.WINTER);
		newTravelRoute3.setBudget(600.0);
		newTravelRoute3.setCategory(Category.FRIENDS);
		newTravelRoute3.setCategoryTwo(CategoryTwo.SPORT);
		newTravelRoute3.setValueAverage(9.1);
		newTravelRoute3.setDescriptionRouteSummary("Lorem Ipsun istin icole hasta que fisticolico de nimiluti.");
		newTravelRoute3.setDescriptionRoute(" Elementum cubilia parturient sociis. Lorem Ipsun istin icole hasta que fisticolico de nimiluti. Bicuris di Cadi sa rese poli que kala pragmatas istem filis mu ke su aresi\\\\r\\\\nTempus pharetra suscipit vulputate mus elementum cubilia parturient sociis");
		newTravelRoute3.setSteps(6);
		newTravelRoute3.setSummaryMap("1 Chefchauen, pintada de azul.; 2 Subida al Yebel El Kelaa; 3 Camino de Alhucemas.; 4 Parque Nacional de Alhucemas; 5 Senderismo en el Rif; 6 Camino, Nador.");
		newTravelRoute3.setCreatedAt(null);
		newTravelRoute3.setUpdatedAt(null);
		//newTravelRoute3.setQrActivation(false);
		
		travelRouteRepository.save(newTravelRoute3);
		
		
		
		return travelRouteRepository.findAll();
	}
	
	//busquedas
	
	public List<TravelRoute>findByDestination(String destination){
		return travelRouteRepository.findByDestinationIgnoreCaseContaining(destination);
	}
	
}
