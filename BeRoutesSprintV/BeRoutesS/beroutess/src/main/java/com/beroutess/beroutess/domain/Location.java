package com.beroutess.beroutess.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "location")
public class Location {
	
	@Id
	@GeneratedValue
	Long id;
	
    @Column(name = "position")
    Integer position;

    @Column(name = "title_location")
    String titleLocation;

    @Column(name = "description_location")
    String descriptionLocation;

    @Column(name = "x_coordinate")
    Float xCoordinate;

    @Column(name = "y_coordinate")
    Float yCoordinate;

    @Column(name = "qr_activation")
    Boolean qrActivation;

    @Column(name = "qr_description")
    String qrDescription;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @Column(name = "photo_location")
    Long photoLocation;

    @Column(name = "photo_location_id")
    Long photoLocationId;
    

    //o
    @OneToOne(mappedBy = "location")
    @JsonBackReference
    Country country;

    //o
    @OneToOne(mappedBy = "location")
    @JsonBackReference
    Photo photo;

    //o
    @OneToOne(mappedBy = "location")
    @JsonBackReference
    Qr qr;

    //o
    //Funciona esta notación para no mostrar travelRoute  @JsonIgnoreProperties("locations")
    @ManyToOne
    @JsonBackReference   
    TravelRoute travelRoute;

	public Long getId() {
		return id;
	}

	public Integer getPosition() {
		return position;
	}

	public String getTitleLocation() {
		return titleLocation;
	}

	public String getDescriptionLocation() {
		return descriptionLocation;
	}

	public Float getxCoordinate() {
		return xCoordinate;
	}

	public Float getyCoordinate() {
		return yCoordinate;
	}

	public Boolean getQrActivation() {
		return qrActivation;
	}

	public String getQrDescription() {
		return qrDescription;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public Long getPhotoLocation() {
		return photoLocation;
	}

	public Long getPhotoLocationId() {
		return photoLocationId;
	}

	public Country getCountry() {
		return country;
	}

	public Photo getPhoto() {
		return photo;
	}

	public Qr getQr() {
		return qr;
	}

	public TravelRoute getTravelRoute() {
		return travelRoute;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setTitleLocation(String titleLocation) {
		this.titleLocation = titleLocation;
	}

	public void setDescriptionLocation(String descriptionLocation) {
		this.descriptionLocation = descriptionLocation;
	}

	public void setxCoordinate(Float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(Float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void setQrActivation(Boolean qrActivation) {
		this.qrActivation = qrActivation;
	}

	public void setQrDescription(String qrDescription) {
		this.qrDescription = qrDescription;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setPhotoLocation(Long photoLocation) {
		this.photoLocation = photoLocation;
	}

	public void setPhotoLocationId(Long photoLocationId) {
		this.photoLocationId = photoLocationId;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public void setQr(Qr qr) {
		this.qr = qr;
	}

	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}

    
    
}
