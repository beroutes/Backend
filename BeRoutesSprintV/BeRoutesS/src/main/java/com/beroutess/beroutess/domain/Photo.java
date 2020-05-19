package com.beroutess.beroutess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name = "photo")

public class Photo {
	@Id
	@GeneratedValue
	Long id;
	
    @Column(name = "title_photo")
    String titlePhoto;

    @Column(name = "description_photo")
    String descriptionPhoto;

    @Column(name = "photo_main")
    Boolean photoMain;

    @Column(name = "photo_map")
    Boolean photoMap;

    @Column(name = "photo_location")
    Boolean photoLocation;
    
    @Column(name = "photo_profile")
    Boolean photoProfile;

    @Column(name = "url_photo")
    String urlPhoto;

    @Column(name = "code_photo")
    Integer codePhoto;

    @Lob
    @Column(name = "image_route")
    byte[] imageRoute;

    @Column(name = "image_route_content_type")
    String imageRouteContentType;
    
    /*
    //o
    @OneToOne
    @JsonIgnore
    Location location;

    //o
    @OneToOne
    @JsonManagedReference
    UserProfile userProfile;

    //o
    @ManyToOne
    @JsonManagedReference
    TravelRoute travelRoute;
*/
	public Long getId() {
		return id;
	}

	public String getTitlePhoto() {
		return titlePhoto;
	}

	public String getDescriptionPhoto() {
		return descriptionPhoto;
	}

	public Boolean getPhotoMain() {
		return photoMain;
	}

	public Boolean getPhotoMap() {
		return photoMap;
	}

	public Boolean getPhotoLocation() {
		return photoLocation;
	}

	public Boolean getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(Boolean photoProfile) {
		this.photoProfile = photoProfile;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public Integer getCodePhoto() {
		return codePhoto;
	}

	public byte[] getImageRoute() {
		return imageRoute;
	}

	public String getImageRouteContentType() {
		return imageRouteContentType;
	}
/*
	public Location getLocation() {
		return location;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public TravelRoute getTravelRoute() {
		return travelRoute;
	}
*/
	public void setId(Long id) {
		this.id = id;
	}

	public void setTitlePhoto(String titlePhoto) {
		this.titlePhoto = titlePhoto;
	}

	public void setDescriptionPhoto(String descriptionPhoto) {
		this.descriptionPhoto = descriptionPhoto;
	}

	public void setPhotoMain(Boolean photoMain) {
		this.photoMain = photoMain;
	}

	public void setPhotoMap(Boolean photoMap) {
		this.photoMap = photoMap;
	}

	public void setPhotoLocation(Boolean photoLocation) {
		this.photoLocation = photoLocation;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public void setCodePhoto(Integer codePhoto) {
		this.codePhoto = codePhoto;
	}

	public void setImageRoute(byte[] imageRoute) {
		this.imageRoute = imageRoute;
	}

	public void setImageRouteContentType(String imageRouteContentType) {
		this.imageRouteContentType = imageRouteContentType;
	}
/*
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}
*/	

    
}
