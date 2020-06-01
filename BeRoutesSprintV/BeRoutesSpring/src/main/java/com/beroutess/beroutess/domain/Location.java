package com.beroutess.beroutess.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    Double xCoordinate;

    @Column(name = "y_coordinate")
    Double yCoordinate;

    @Column(name = "qr_activation")
    Boolean qrActivation;

    @Column(name = "qr_description")
    String qrDescription;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    

    /*
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
*/
    ///*
    //ooo
    //Funciona tambien esta notación para no mostrar travelRoute  @JsonIgnoreProperties("locations")
    @ManyToOne
    //@JsonIgnoreProperties//("locations")
    @JsonBackReference 
    //@JsonIgnore
    TravelRoute travelRoute;
    //*/
    //*/

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

	public Double getxCoordinate() {
		return xCoordinate;
	}

	public Double getyCoordinate() {
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

/*
	public Country getCountry() {
		return country;
	}

	public Photo getPhoto() {
		return photo;
	}

	public Qr getQr() {
		return qr;
	}
*/
	public TravelRoute getTravelRoute() {
		return travelRoute;
	}
//*/
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

	public void setxCoordinate(Double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(Double yCoordinate) {
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
/*
	public void setCountry(Country country) {
		this.country = country;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public void setQr(Qr qr) {
		this.qr = qr;
	}
*/
	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}

  //*/  
    
}
