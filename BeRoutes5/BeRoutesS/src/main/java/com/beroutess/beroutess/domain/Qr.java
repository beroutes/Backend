package com.beroutess.beroutess.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "qr")
public class Qr {
	
	@Id
	@GeneratedValue
	Long id;
	
	@Column(name = "qr_description")
    String qrDescription;

    @Column(name = "data_1")
    Double data1;

    @Column(name = "data_2")
    Double data2;

    @Column(name = "data_3")
    Double data3;
    /*
    //o
    @OneToOne
    @JsonManagedReference
    Location location;

    //o
    @ManyToOne
    @JsonManagedReference
    TravelRoute travelRoute;
*/
	public Long getId() {
		return id;
	}

	public String getQrDescription() {
		return qrDescription;
	}

	public Double getData1() {
		return data1;
	}

	public Double getData2() {
		return data2;
	}

	public Double getData3() {
		return data3;
	}
/*
	public Location getLocation() {
		return location;
	}

	public TravelRoute getTravelRoute() {
		return travelRoute;
	}
*/
	public void setId(Long id) {
		this.id = id;
	}

	public void setQrDescription(String qrDescription) {
		this.qrDescription = qrDescription;
	}

	public void setData1(Double data1) {
		this.data1 = data1;
	}

	public void setData2(Double data2) {
		this.data2 = data2;
	}

	public void setData3(Double data3) {
		this.data3 = data3;
	}
/*
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setTravelRoute(TravelRoute travelRoute) {
		this.travelRoute = travelRoute;
	}
*/
    

}
