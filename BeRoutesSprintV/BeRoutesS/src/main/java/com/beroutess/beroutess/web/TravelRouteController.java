package com.beroutess.beroutess.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.beroutess.beroutess.domain.TravelRoute;
import com.beroutess.beroutess.repository.TravelRouteRepository;
import com.beroutess.beroutess.service.TravelRouteService;
import com.beroutess.beroutess.web.error.CustomBeRoutesError;




@RestController

public class TravelRouteController {
	
	TravelRouteService travelRouteService;
	TravelRouteRepository travelRouteRepository;


	
	public TravelRouteController(TravelRouteService travelRouteService, TravelRouteRepository travelRouteRepository) {
	
		this.travelRouteService = travelRouteService;
		this.travelRouteRepository = travelRouteRepository;
	}
	
	@GetMapping(path = "/travelRoutes")	
	List<TravelRoute> getTravelRoutes(
			@RequestParam(required = false)String destination,
			@RequestParam(required = false)Double value,
	        @RequestParam(required = false) Long favoritesId,
	        @RequestParam(required = false) Long myRoutesId)
	{
		if(destination!=null) {
			return travelRouteService.findByDestination(destination);
		}
		if(value!=null) {
			return travelRouteRepository.findByValueAverageGreaterThanEqual(value);
		}
		if(favoritesId!=null) {
			return travelRouteRepository.findByFavorites_userProfile_idEquals(favoritesId);
		}
		if(myRoutesId!=null) {
			return travelRouteRepository.findByUserProfile_idEquals(myRoutesId);
		}
		
		else
	
		return travelRouteService.getTravelRoutes();
	}
/*
	@GetMapping(path = "/travelRoutes")	
	List<TravelRoute> getTravelRoutes(@RequestParam(required=false,name="userId")Integer id){
		if(id != null){
			return travelRouteRepository.findByUserProfile_favorite(id);
		}else
		return travelRouteService.getTravelRoutes();
	}
*/
	/*
	@GetMapping(path = "/travelRoute/{id}")
	TravelRoute getTravelRoute (@PathVariable Long id) {
		return travelRouteService.getTravelRoute(id);
	}
	*/
	@GetMapping(path = "/travelRoute/{id}")
	TravelRoute findById(@PathVariable Long id){
		if (travelRouteService.findById(id).isPresent()) {
			return travelRouteService.findById(id).get();
		}else {
			throw new CustomBeRoutesError("We can't find the route.");
		}
						
	}
	
	@PostMapping(path = "/travelRoute")
	Long addTravelRoute (@RequestBody TravelRoute travelRoute) {
		return travelRouteService.addTravelRoute(travelRoute);
	}
	
	@DeleteMapping(path="/travelRoute/{id}")
	String deleteTravelRoute(@PathVariable Long id) {
		travelRouteService.deleteTravelRoute(id);
		return(" DELETE OK");
	}
	
	@PutMapping(path = "/travelRoute/{id}")
	TravelRoute editTravelRoute(@RequestBody TravelRoute travelRoute,@PathVariable Long id) {
		travelRouteService.editTravelRoute(id, travelRoute);
		return travelRoute;
	}
	
	//Get para inicializar los datos de la app
	@GetMapping(path = "/travelRoutes/init")	
	List<TravelRoute> getTravelRoutesInit(){
		
		List<TravelRoute> theList = new ArrayList<TravelRoute>();
		theList = travelRouteService.getTravelRoutes();
		int a = theList.size();
		
		if(a==0) {
			return	travelRouteService.initTraveRoute();
			
		}else
		
		return travelRouteService.getTravelRoutes();
	}
	
	//Get de busqueda 

}
