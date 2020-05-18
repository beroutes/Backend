package com.beroutess.beroutess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "valuation")
public class Valuation {
	@Id
	@GeneratedValue
	Long id;
	
    @Column(name = "rating")
     Integer rating;

    @Column(name = "comment")
    String comment;
/*
    //o
    @ManyToOne
    @JsonManagedReference
    TravelRoute travelRoute;

    //o
    @ManyToOne
    @JsonManagedReference
    UserProfile userProfile;
*/
	public Long getId() {
		return id;
	}

	public Integer getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}
/*
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

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
/*
	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
 */   
    

}
