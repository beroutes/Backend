package com.beroutess.beroutess.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beroutess.beroutess.domain.Country;
import com.beroutess.beroutess.repository.CountryRepository;
import com.beroutess.beroutess.service.CountryService;
import com.beroutess.beroutess.web.error.CustomBeRoutesError;

@RestController
public class CountryController

{

	CountryService countryService;
	
	CountryRepository countryRepository;
	
	
	
	
	public CountryController(CountryService countryService, CountryRepository countryRepository) {
	
		this.countryService = countryService;
		this.countryRepository = countryRepository;
	}
	
///*
	@GetMapping(path = "/countries")	
	List<Country> getCountries(){
		return countryService.getCountries();
	}
//*/
	/*//Otro modo de hacer el get mediante el repository directamente
	 * 
	@GetMapping(path = "/countries")
	List<Country> getCountries(){
		return countryRepository.findAll();
		}
	*/	
	
	/*
	@GetMapping(path = "/country/{id}")
	Country getCountry (@PathVariable Long id) {
		return countryService.getCountry(id);
	}
	*/
	
	@GetMapping(path = "country/{id}")
	Country findById(@PathVariable Long id){
		if (countryService.findById(id).isPresent()) {
			return countryService.findById(id).get();
		}else {
			throw new CustomBeRoutesError("We can't find the country.");
		}
						
	}
	///*
	@PostMapping(path = "/country")
	Long addCountry (@RequestBody Country country) {
		return countryService.addCountry(country);
	}
	
	//*/
	/*//Otro modo de hacer el post llamando al repository directamente
	@PostMapping(path = "/country")
	Long addCountry (@RequestBody Country country) {
		 countryRepository.save(country);
		 return country.getId();
	}
	*/
	//@PreAuthorize(value = "hasRole('ROLE_TRAVELLER')")
	@DeleteMapping(path="/country/{id}")
	String deleteCountry(@PathVariable Long id) {
		countryService.deleteCountry(id);
		return(" DELETE OK");
	}
	
	@PutMapping(path = "/country/{id}")
	Country editCountry (@PathVariable Long id, @RequestBody Country country) {
		countryService.editCountry(id, country);
		return country;
	}
	
	//Get para inicializar los datos de la app
	@GetMapping(path = "/countries/init")	
	List<Country> initCountries(){
		if (countryService.getCountries().size()==0) {
			return countryService.initCountries();
		}else
			return countryService.getCountries();
	}
	
	@PostMapping(path = "/countries/init0")	
	String initCountry0(){
		countryService.initCountry0();
		return "Initialize0 Ok";
	}
	
	@PostMapping(path = "/countries/init1")	
	String initCountry1(){
		countryService.initCountry1();
		return "Initialize1 Ok";
	}
	
	
}
