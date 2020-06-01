package com.beroutess.beroutess.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user_profile")
public class UserProfile {
	
	@Id
	@GeneratedValue
	Long id;

	@Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "telephone")
    Integer telephone;

    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "age")
    Integer age;

    @Column(name = "biography")
    String biography;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @Column(name = "follower")
    Integer follower;

    @Column(name = "followed")
    Integer followed;
    /*
    //o
    @OneToOne(mappedBy = "userProfile")
    @JsonManagedReference
    Country country;
    
    //o
    @OneToOne(mappedBy = "userProfile")
    @JsonBackReference
    Photo photo;
    */
    //o
    @OneToMany(mappedBy = "userProfile")
    //@JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    List<TravelRoute>travelRoutes;
    /*
    //o
    @OneToMany(mappedBy = "userProfile")
    @JsonBackReference
    List<Valuation>valuations;
    
    //o
    @OneToMany(mappedBy = "userProfile")
    @JsonBackReference
    List<Following>followings;
    */
    
    //o
    @OneToMany(mappedBy ="userProfile")
    
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    
    List<Favorite>favorites;
    //*/

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Integer getAge() {
		return age;
	}

	public String getBiography() {
		return biography;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public Integer getFollower() {
		return follower;
	}

	public Integer getFollowed() {
		return followed;
	}
/*
	public Country getCountry() {
		return country;
	}

	public Photo getPhoto() {
		return photo;
	}
*/
	public List<TravelRoute> getTravelRoutes() {
		return travelRoutes;
	}
/*
	public List<Valuation> getValuations() {
		return valuations;
	}

	public List<Following> getFollowings() {
		return followings;
	}
*/
	public List<Favorite> getFavorites() {
		return favorites;
	}
//*/
	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	public void setFollowed(Integer followed) {
		this.followed = followed;
	}
/*
	public void setCountry(Country country) {
		this.country = country;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
*/
	public void setTravelRoutes(List<TravelRoute> travelRoutes) {
		this.travelRoutes = travelRoutes;
	}
/*
	public void setValuations(List<Valuation> valuations) {
		this.valuations = valuations;
	}

	public void setFollowings(List<Following> followings) {
		this.followings = followings;
	}
*/
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
  //  */
	
  //No tengo clara esta relación.//o
    //@ManyToOne    
    //Following following;
    
    
    
    
}
