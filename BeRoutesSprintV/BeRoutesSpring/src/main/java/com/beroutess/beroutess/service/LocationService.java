package com.beroutess.beroutess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.Location;

import com.beroutess.beroutess.repository.LocationRepository;

@Service
public class LocationService {
	LocationRepository locationRepository;

	public LocationService(LocationRepository locationRepository) {
	
		this.locationRepository = locationRepository;
	}
	
	
	public List<Location>getLocations(){
		return locationRepository.findAll();
	}
	
	public Optional<Location> findById(Long id) {
		return locationRepository.findById(id);
	}

	public Long addLocation(Location location) {
		Location locationSaved = locationRepository.save(location);
		return locationSaved.getId();		
	}
	
	public Long deleteLocation(Long id) {
		locationRepository.delete(locationRepository.getOne(id));
		return locationRepository.count();
	}
	
	public Location editLocation(Long id, Location location) {
		Location locationSaved = locationRepository.getOne(id);
	
		locationSaved.setPosition(location.getPosition());
		locationSaved.setTitleLocation(location.getTitleLocation());
		locationSaved.setDescriptionLocation(location.getDescriptionLocation());
		locationSaved.setxCoordinate(location.getxCoordinate());
		locationSaved.setyCoordinate(location.getyCoordinate());
		locationSaved.setQrActivation(location.getQrActivation());
		locationSaved.setQrDescription(location.getQrDescription());
		locationSaved.setCreatedAt(location.getCreatedAt());
		locationSaved.setUpdatedAt(location.getUpdatedAt());
		//locationSaved.setPhotoLocationDescription(location.getPhotoLocationDescription());
	    //locationSaved.setPhotoLocationId(location.getPhotoLocationId());
	    /*
		locationSaved.setCountry(location.getCountry());
		locationSaved.setPhoto(location.getPhoto());
		locationSaved.setQr(location.getQr());
		*/
		
	    locationSaved.setTravelRoute(location.getTravelRoute());
		//*/
		
		locationRepository.save(locationSaved);
		return locationSaved;
		
	}
	
	
	public List<Location> initLocations(){
		
		//Corresponde al country id 1 y al travelRoute id 0
		Location newLocation0 = new Location();
		
		newLocation0.setPosition(1);
		newLocation0.setTitleLocation("Union Square");
		newLocation0.setDescriptionLocation("Praesent lacinia volutpat nisl, a feugiat magna gravida eu. Integer ut condimentum lorem, eget fermentum");
		newLocation0.setxCoordinate(-122.4075169);
		newLocation0.setyCoordinate(37.7879797);
		newLocation0.setQrActivation(false);
		newLocation0.setQrDescription("");
		newLocation0.setCreatedAt(null);
		newLocation0.setUpdatedAt(null);
		//newLocation0.setPhotoLocationDescription("Curabitur laoreet lacus vitae lectus luctus, id interdum libero ultrices. Phasellus nec maximus metu");
		//newLocation0.setPhotoLocationId(null);
		
		/*
		newLocation0.setCountry(null);
		newLocation0.setPhoto(null);
		newLocation0.setQr(null);
		newLocation0.setTravelRoute(null);
	    */
	    locationRepository.save(newLocation0);
	    
	    //Corresponde al country id 1 y al travelRoute id 0
		Location newLocation1 = new Location();
		
		newLocation1.setPosition(2);
		newLocation1.setTitleLocation("Twin Peaks");
		newLocation1.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libero ultrices. Phasellus nec maximus metu");
		newLocation1.setxCoordinate(-122.44648);
		newLocation1.setyCoordinate(37.75464);
		newLocation1.setQrActivation(false);
		newLocation1.setQrDescription("");
		newLocation1.setCreatedAt(null);
		newLocation1.setUpdatedAt(null);
		//newLocation1.setPhotoLocationDescription("Proin luctus vel massa ac imperdiet. Praesent suscipit fermentum erat");
		//newLocation1.setPhotoLocationId(null);
		/*
		newLocation1.setCountry(null);
		newLocation1.setPhoto(null);
		newLocation1.setQr(null);
		newLocation1.setTravelRoute(null);
	    */
	    locationRepository.save(newLocation1);
	    
	    //Corresponde al country id 1 y al travelRoute id 0
		Location newLocation2 = new Location();
		
		newLocation2.setPosition(3);
		newLocation2.setTitleLocation("Sausalito");
		newLocation2.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
		newLocation2.setxCoordinate(-122.4854694);
		newLocation2.setyCoordinate(37.8590272);
		newLocation2.setQrActivation(false);
		newLocation2.setQrDescription("");
		newLocation2.setCreatedAt(null);
		newLocation2.setUpdatedAt(null);
		//newLocation2.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
		//newLocation2.setPhotoLocationId(null);
		/*
		newLocation2.setCountry(null);
		newLocation2.setPhoto(null);
		newLocation2.setQr(null);
		newLocation2.setTravelRoute(null);
	    */
	    locationRepository.save(newLocation2);
	    
	    //Corresponde al country id 1 y al travelRoute id 0
		Location newLocation3 = new Location();
		
		newLocation3.setPosition(4);
		newLocation3.setTitleLocation("ChinaTown");
		newLocation3.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
		newLocation3.setxCoordinate(-122.4063757);
		newLocation3.setyCoordinate(37.7943011);
		newLocation3.setQrActivation(false);
		newLocation3.setQrDescription("");
		newLocation3.setCreatedAt(null);
		newLocation3.setUpdatedAt(null);
		//newLocation3.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
		//newLocation3.setPhotoLocationId(null);
		/*
		newLocation3.setCountry(null);
		newLocation3.setPhoto(null);
		newLocation3.setQr(null);
		newLocation3.setTravelRoute(null);
	    */
	    locationRepository.save(newLocation3);  	    

	    
	    //Corresponde al country id 1 y al travelRoute id 0
		Location newLocation5 = new Location();
		
		newLocation5.setPosition(5);
		newLocation5.setTitleLocation("Point Reyes ");
		newLocation5.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
		newLocation5.setxCoordinate(-117.323303);
		newLocation5.setyCoordinate(33.248039);
		newLocation5.setQrActivation(false);
		newLocation5.setQrDescription("");
		newLocation5.setCreatedAt(null);
		newLocation5.setUpdatedAt(null);
		//newLocation5.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
		//newLocation5.setPhotoLocationId(null);
		/*
		newLocation5.setCountry(null);
		newLocation5.setPhoto(null);
		newLocation5.setQr(null);
		newLocation5.setTravelRoute(null);
	    */
	    locationRepository.save(newLocation5);
	    
	    
	   //Corresponde al country id 0 y al travelRoute id 1
		Location newLocation6 = new Location();
		
		newLocation6.setPosition(1);
		newLocation6.setTitleLocation("Göreme");
		newLocation6.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
		newLocation6.setxCoordinate(34.8296234);
		newLocation6.setyCoordinate(38.642089);
		newLocation6.setQrActivation(false);
		newLocation6.setQrDescription("");
		newLocation6.setCreatedAt(null);
		newLocation6.setUpdatedAt(null);
		//newLocation6.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
		//newLocation6.setPhotoLocationId(null);
		/*
		newLocation6.setCountry(null);
		newLocation6.setPhoto(null);
		newLocation6.setQr(null);
		newLocation6.setTravelRoute(null);
	    */
	    locationRepository.save(newLocation6);
	    
		   //Corresponde al country id 0 y al travelRoute id 1
			Location newLocation7 = new Location();
			
			newLocation7.setPosition(2);
			newLocation7.setTitleLocation("Uchisar");
			newLocation7.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
			newLocation7.setxCoordinate(34.8046138);
			newLocation7.setyCoordinate(38.6293826);
			newLocation7.setQrActivation(false);
			newLocation7.setQrDescription("");
			newLocation7.setCreatedAt(null);
			newLocation7.setUpdatedAt(null);
			//newLocation7.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
			//newLocation7.setPhotoLocationId(null);
			/*
			newLocation7.setCountry(null);
			newLocation7.setPhoto(null);
			newLocation7.setQr(null);
			newLocation7.setTravelRoute(null);
		    */
		    locationRepository.save(newLocation7);
		    
			   //Corresponde al country id 0 y al travelRoute id 1
			Location newLocation8 = new Location();
			
			newLocation8.setPosition(3);
			newLocation8.setTitleLocation("Ortahisar");
			newLocation8.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
			newLocation8.setxCoordinate(39.706678);
			newLocation8.setyCoordinate(41.0013246);
			newLocation8.setQrActivation(false);
			newLocation8.setQrDescription("");
			newLocation8.setCreatedAt(null);
			newLocation8.setUpdatedAt(null);
			//newLocation8.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
			//newLocation8.setPhotoLocationId(null);
			/*
			newLocation8.setCountry(null);
			newLocation8.setPhoto(null);
			newLocation8.setQr(null);
			newLocation8.setTravelRoute(null);
		    */
		    locationRepository.save(newLocation8);
		    
			   //Corresponde al country id 0 y al travelRoute id 1
			Location newLocation9 = new Location();
			
			newLocation9.setPosition(4);
			newLocation9.setTitleLocation("Ürgup");
			newLocation9.setDescriptionLocation("Curabitur laoreet lacus vitae lectus luctus, id interdum libers vel massa ac imperdiet. Praesent suscipito ultrices. Phasellus nec maximus metu");
			newLocation9.setxCoordinate(34.9589020075008);
			newLocation9.setyCoordinate(38.5928197);
			newLocation9.setQrActivation(false);
			newLocation9.setQrDescription("");
			newLocation9.setCreatedAt(null);
			newLocation9.setUpdatedAt(null);
			//newLocation9.setPhotoLocationDescription("Pinterdum libero ultrices. Phasellus necroin luctu fermentum erat");
			//newLocation9.setPhotoLocationId(null);
			/*
			newLocation9.setCountry(null);
			newLocation9.setPhoto(null);
			newLocation9.setQr(null);
			newLocation9.setTravelRoute(null);
		    */
		    locationRepository.save(newLocation9);
		  
	    	        
	    
	    return locationRepository.findAll();
		
	}
	
	
}
