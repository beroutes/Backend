package com.beroutess.beroutess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beroutess.beroutess.domain.Country;
import com.beroutess.beroutess.service.CountryService;

@RestController
public class CountryController {

	@Autowired 
	CountryService countryService;
	
	
	@GetMapping(path = "/countries")	
	List<Country> getCountries(){
		return countryService.getCountries();
	}
	
	@GetMapping(path = "/country /{id}")
	Country getCountry (@PathVariable Long id) {
		return countryService.getCountry(id);
	}
	
	@PostMapping(path = "/country")
	Long addCountry (@RequestBody Country country) {
		return countryService.addCountry(country);
	}
	
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
