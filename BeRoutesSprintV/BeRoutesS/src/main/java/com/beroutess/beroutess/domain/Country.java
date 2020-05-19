package com.beroutess.beroutess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
//@Table(name = "country")
public class Country {
	
	@Id
	@GeneratedValue
	Long id;
	
	 @Column(name = "country_name")
	 String countryName;

	 @Column(name = "region")
	 String region;

	 @Column(name = "city")
	 String city;
	 
	 
	 /*
     //o //probaré a no mostrar tampoco el loaction en el country con JsonIgnoreProperties pero se ve aunque vacio creo
	 @OneToOne
	 @JsonIgnoreProperties
	 Location location;
	 
	 //o
	 @OneToOne
	 @JsonBackReference
	 TravelRoute travelRoute;

	 //o
	 @OneToOne
	 @JsonBackReference
	 UserProfile userProfile;
	 
	*/

	public Long getId() {
		return id;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getRegion() {
		return region;
	}

	public String getCity() {
		return city;
	}

	/*
	public Location getLocation() {
		return location;
	}

	public TravelRoute getTravelRoute() {
		return travelRoute;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}
	*/
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	*/


}
