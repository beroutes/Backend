package com.beroutess.beroutess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@Entity
@Table(name = "favorite")
public class Favorite {
	
	@Id
	@GeneratedValue
	Long id;

    @Column(name = "jhi_like")
    Boolean like;

    @Column(name = "not_like")
    Boolean notLike;

    
    
   //  /*
    //o //Molaría que enseñara solo el id el json
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    TravelRoute travelRoute;
//*/
    //o    
    @ManyToOne
    @JsonBackReference
    UserProfile userProfile;
    //*/
    

	public Long getId() {
		return id;
	}

	public Boolean getLike() {
		return like;
	}

	public Boolean getNotLike() {
		return notLike;
	}

//	/*
	public TravelRoute getTravelRoute() {
		return travelRoute;
	}
//*/
	public UserProfile getUserProfile() {
		return userProfile;
	}
	//*/
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setLike(Boolean like) {
		this.like = like;
	}

	public void setNotLike(Boolean notLike) {
		this.notLike = notLike;
	}

	///*
	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}
//*/
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
  //  */
    
    
}
