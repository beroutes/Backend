package com.beroutess.beroutess.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.Country;
import com.beroutess.beroutess.repository.CountryRepository;

@Service
public class CountryService {
	
	//Llamamos a la información del repositorio
	
	CountryRepository countryRepository;
	
	
   //Inicializamos con un constructor
	public CountryService(CountryRepository countryRepository) {
	
		this.countryRepository = countryRepository;
	}
	
	///Inicializamos 2 Countries manualmente mediante otro constructor
	
	/*
	 * public CountryService() {
		super();
		
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
	}*/
	
	///Los métodos del servicio
	
	public List<Country>getCountries(){
		return countryRepository.findAll();
	}
	
	public Country getCountry(Long id) {
		return countryRepository.getOne(id);
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

	///Metodos para icializar 2 Countries 
	
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
	
	
		
	
}
