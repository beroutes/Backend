package com.beroutess.beroutess.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.beroutess.beroutess.domain.Location;
import com.beroutess.beroutess.repository.LocationRepository;
import com.beroutess.beroutess.service.LocationService;
import com.beroutess.beroutess.web.error.CustomBeRoutesError;

@RestController
public class LocationController {
	

	@Autowired
	LocationService locationService;
	@Autowired
	LocationRepository locationRepository;
	
	
	
	@GetMapping(path = "/locations")	
	List<Location> getLocations(@RequestParam(required=false,name="name")String name){
		if(name != null){
			return locationRepository.findByTravelRoute_destination(name);
		}else
		return locationService.getLocations();
	}
	
	@GetMapping(path = "/location/{id}")
	Location findById(@PathVariable Long id){
		if (locationService.findById(id).isPresent()) {
			return locationService.findById(id).get();
		}else {
			throw new CustomBeRoutesError("We can't find the location.");
		}
						
	}
	
	@PostMapping(path = "/location")
	Long addLocation (@RequestBody Location location) {
		return locationService.addLocation(location);
	}
	
	@DeleteMapping(path="/location/{id}")
	String deleteLocation(@PathVariable Long id) {
		locationService.deleteLocation(id);
		return(" DELETE OK");
	}
	
	@PutMapping(path = "/location/{id}")
	Location editLocation (@PathVariable Long id, @RequestBody Location location) {
		locationService.editLocation(id, location);
		return location;
	}
	
	//Get para inicializar los datos de la app
	@GetMapping(path = "/locations/init")	
	List<Location> initLocations(){
		if (locationService.getLocations().size()==0) {
			return locationService.initLocations();
		}else
			return locationService.getLocations();
	}
	
}
