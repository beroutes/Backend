package com.beroutess.beroutess.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.beroutess.beroutess.domain.enumeration.Category;
import com.beroutess.beroutess.domain.enumeration.CategoryTwo;
import com.beroutess.beroutess.domain.enumeration.Continent;
import com.beroutess.beroutess.domain.enumeration.Season;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "travel_route")
public class TravelRoute {
	@Id
	@GeneratedValue
	Long id;
	
	    @Column(name = "title_route")
	    String titleRoute;

	    @Column(name = "destination")
	    String destination;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "continent")
	    Continent continent;

	    @Column(name = "days")
	    Integer days;

	    @Column(name = "weeks")
	    Integer weeks;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "season")
	    Season season;

	    @Column(name = "budget")
	    Double budget;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "category")
	    Category category;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "category_two")
	    CategoryTwo categoryTwo;

	    @Column(name = "value_average")
	    Double valueAverage;

	    @Column(name = "description_route_summary")
	    String descriptionRouteSummary;

	    @Column(name = "description_route")
	    String descriptionRoute;

	    @Column(name = "steps")
	    Integer steps;

	    @Column(name = "summary_map")
	    String summaryMap;

	    @Column(name = "created_at")
	    LocalDate createdAt;

	    @Column(name = "updated_at")
	    LocalDate updatedAt;
/*
	    @Column(name = "qr_activation")
	    Boolean qrActivation;
	    
	*/
	    
	    /*
        //o
	    @OneToOne(mappedBy = "travelRoute")
	    @JsonManagedReference
	    Country country;
	    */
	    ///*
	    //ooo//Muestra el array de locations
	    @OneToMany(mappedBy ="travelRoute")
	    //@JsonManagedReference
	    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	    List<Location>locations;
	    //*/
	    ///*
	    //o
	    @OneToMany(mappedBy = "travelRoute")
	    //@JsonIgnoreProperties({"travelRoute"})
	    @JsonBackReference
	    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	    List<Favorite>favorites;
	    /*
	    //o
	    @OneToMany(mappedBy = "travelRoute")
	    @JsonBackReference
	    List<Photo>photos;
	    
	    //o
	    @OneToMany(mappedBy = "travelRoute")
	    @JsonBackReference
	    List<Valuation>valuations;
	    
	    //o
	    @OneToMany(mappedBy = "travelRoute")
	    @JsonBackReference
	    List<Qr>qrs;
	    */ 
	    
	    //o
	    @ManyToOne
	    //@JsonBackReference
	    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	    UserProfile userProfile;
	    
        
		public Long getId() {
			return id;
		}

		public String getTitleRoute() {
			return titleRoute;
		}

		public String getDestination() {
			return destination;
		}

		public Continent getContinent() {
			return continent;
		}

		public Integer getDays() {
			return days;
		}

		public Integer getWeeks() {
			return weeks;
		}

		public Season getSeason() {
			return season;
		}

		public Double getBudget() {
			return budget;
		}

		public Category getCategory() {
			return category;
		}

		public CategoryTwo getCategoryTwo() {
			return categoryTwo;
		}

		public Double getValueAverage() {
			return valueAverage;
		}

		public String getDescriptionRouteSummary() {
			return descriptionRouteSummary;
		}

		public String getDescriptionRoute() {
			return descriptionRoute;
		}

		public Integer getSteps() {
			return steps;
		}

		public String getSummaryMap() {
			return summaryMap;
		}

		public LocalDate getCreatedAt() {
			return createdAt;
		}

		public LocalDate getUpdatedAt() {
			return updatedAt;
		}
/*
		public Boolean getQrActivation() {
			return qrActivation;
		}
*/
/*
		public Country getCountry() {
			return country;
		}
*/
		public List<Location> getLocations() {
			return locations;
		}
///*
		public List<Favorite> getFavorites() {
			return favorites;
		}
/*
		public List<Photo> getPhotos() {
			return photos;
		}

		public List<Valuation> getValuations() {
			return valuations;
		}

		public List<Qr> getQrs() {
			return qrs;
		}
*/
		public UserProfile getUserProfile() {
			return userProfile;
		}
		

		public void setId(Long id) {
			this.id = id;
		}

		public void setTitleRoute(String titleRoute) {
			this.titleRoute = titleRoute;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public void setContinent(Continent continent) {
			this.continent = continent;
		}

		public void setDays(Integer days) {
			this.days = days;
		}

		public void setWeeks(Integer weeks) {
			this.weeks = weeks;
		}

		public void setSeason(Season season) {
			this.season = season;
		}

		public void setBudget(Double budget) {
			this.budget = budget;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public void setCategoryTwo(CategoryTwo categoryTwo) {
			this.categoryTwo = categoryTwo;
		}

		public void setValueAverage(Double valueAverage) {
			this.valueAverage = valueAverage;
		}

		public void setDescriptionRouteSummary(String descriptionRouteSummary) {
			this.descriptionRouteSummary = descriptionRouteSummary;
		}

		public void setDescriptionRoute(String descriptionRoute) {
			this.descriptionRoute = descriptionRoute;
		}

		public void setSteps(Integer steps) {
			this.steps = steps;
		}

		public void setSummaryMap(String summaryMap) {
			this.summaryMap = summaryMap;
		}

		public void setCreatedAt(LocalDate createdAt) {
			this.createdAt = createdAt;
		}

		public void setUpdatedAt(LocalDate updatedAt) {
			this.updatedAt = updatedAt;
		}
/*
		public void setQrActivation(Boolean qrActivation) {
			this.qrActivation = qrActivation;
		}
*/
		
/*
		public void setCountry(Country country) {
			this.country = country;
		}
*/
		public void setLocations(List<Location> locations) {
			this.locations = locations;
		}
///*
		public void setFavorites(List<Favorite> favorites) {
			this.favorites = favorites;
		}
/*
		public void setPhotos(List<Photo> photos) {
			this.photos = photos;
		}

		public void setValuations(List<Valuation> valuations) {
			this.valuations = valuations;
		}

		public void setQrs(List<Qr> qrs) {
			this.qrs = qrs;
		}
   */
		public void setUserProfile(UserProfile userProfile) {
			this.userProfile = userProfile;
		}
	  

}
