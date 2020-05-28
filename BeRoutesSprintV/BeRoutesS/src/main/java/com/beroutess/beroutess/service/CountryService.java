package com.beroutess.beroutess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.Country;

import com.beroutess.beroutess.repository.CountryRepository;

@Service
public class CountryService {
	
	//Llamamos a la información del repositorio
	
	CountryRepository countryRepository;
	
	
   //Inicializamos con un constructor el listado guardado en el repositorio y 9 Countries
	public CountryService(CountryRepository countryRepository) {
	
		this.countryRepository = countryRepository;		
	}
	

	
	///Los métodos del servicio
	
	public List<Country>getCountries(){
		return countryRepository.findAll();
	}
	/*
	public Country getCountry(Long id) {
		return countryRepository.getOne(id);
	}
	*/
	public Optional<Country> findById(Long id) {
		return countryRepository.findById(id);
	}

	public Long addCountry (Country country) {
		Country countrySaved = countryRepository.save(country);
		return countrySaved.getId();		
	}
	
	public Long deleteCountry(Long id) {
		countryRepository.delete(countryRepository.getOne(id));
		return countryRepository.count();
	}
	
	public Country editCountry(Long id, Country country) {
		Country countrySaved = countryRepository.getOne(id);
		
		countrySaved.setCountryName(country.getCountryName());			
		countrySaved.setRegion(country.getRegion());
		countrySaved.setCity(country.getCity());
		//countrySaved.setLocation(country.getLocation());
	    //countrySaved.setTravelRoute(country.getTravelRoute());
	    //countrySaved.setUserProfile(country.getUserProfile());
		
		countryRepository.save(countrySaved);
		return countrySaved;
	}

	///Metodos para icializar 2 Countries con post 
	
	public String initCountry0() {
		
		Country newCountry0 = new Country();
		newCountry0.setCountryName("Turkey");
		newCountry0.setRegion("Cappadocia");
		newCountry0.setCity("");
		countryRepository.save(newCountry0);
		
		return "Initialize0 Ok";
	}
	
	public String initCountry1() {
		
		Country newCountry1 = new Country();
		newCountry1.setCountryName("United States");
		newCountry1.setRegion("");
		newCountry1.setCity("San Francisco");
		countryRepository.save(newCountry1);
		
		return "Initialize1 Ok";
	}
	
	
	public List<Country> initCountries() {
		
	    
		// 9 Countries que inicializamos
		 
		Country newCountry0 = new Country();
		newCountry0.setCountryName("Turkey");
		newCountry0.setRegion("Cappadocia");
		newCountry0.setCity("");
		countryRepository.save(newCountry0);
		
		Country newCountry1 = new Country();
		newCountry1.setCountryName("United States");
		newCountry1.setRegion("");
		newCountry1.setCity("San Francisco");
		countryRepository.save(newCountry1);
		
		Country newCountry2 = new Country();
		newCountry2.setCountryName("Greece");
		newCountry2.setRegion("");
		newCountry1.setCity("Athens");
		countryRepository.save(newCountry2);
		
		Country newCountry3 = new Country();
		newCountry3.setCountryName("Morocco");
		newCountry3.setRegion("Rif");
		newCountry3.setCity("");
		countryRepository.save(newCountry3);
		
		Country newCountry4 = new Country();
		newCountry4.setCountryName("Greenland");
		newCountry4.setRegion("Tassiusaq");
		newCountry4.setCity("");
		countryRepository.save(newCountry4);
		
		Country newCountry5 = new Country();
		newCountry5.setCountryName("Italia");
		newCountry5.setRegion("Toscana");
		newCountry5.setCity("Florencia");
		countryRepository.save(newCountry5);
		
		Country newCountry6 = new Country();
		newCountry6.setCountryName("Portugal");
		newCountry6.setRegion("");
		newCountry6.setCity("Lisboa");
		countryRepository.save(newCountry6);
		
		Country newCountry7 = new Country();
		newCountry7.setCountryName("Japon");
		newCountry7.setRegion("");
		newCountry7.setCity("Tokio");
		countryRepository.save(newCountry7);
		
		Country newCountry8 = new Country();
		newCountry8.setCountryName("Spain");
		newCountry8.setRegion("Andalucia");
		newCountry8.setCity("Tarifa");
		countryRepository.save(newCountry8);
		
	    
		
		return countryRepository.findAll();
	}
	
	
		
	
}
