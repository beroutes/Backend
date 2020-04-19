package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "location")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "step_position", nullable = false)
    private Long stepPosition;

    @NotNull
    @Column(name = "title_location", nullable = false)
    private String titleLocation;

    @NotNull
    @Column(name = "description_location", nullable = false)
    private String descriptionLocation;

    @Column(name = "x_coordinate")
    private Long xCoordinate;

    @Column(name = "y_coordinate")
    private Long yCoordinate;

    @Column(name = "step_duration")
    private String stepDuration;

    @Column(name = "qr_code")
    private Long qrCode;

    @Column(name = "qr_description")
    private String qrDescription;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private Duration duration;

    @OneToOne
    @JoinColumn(unique = true)
    private Photo photo;

    @ManyToOne
    @JsonIgnoreProperties("locations")
    private Country country;

    @ManyToOne
    @JsonIgnoreProperties("locations")
    private TravelRoute travelRoute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStepPosition() {
        return stepPosition;
    }

    public Location stepPosition(Long stepPosition) {
        this.stepPosition = stepPosition;
        return this;
    }

    public void setStepPosition(Long stepPosition) {
        this.stepPosition = stepPosition;
    }

    public String getTitleLocation() {
        return titleLocation;
    }

    public Location titleLocation(String titleLocation) {
        this.titleLocation = titleLocation;
        return this;
    }

    public void setTitleLocation(String titleLocation) {
        this.titleLocation = titleLocation;
    }

    public String getDescriptionLocation() {
        return descriptionLocation;
    }

    public Location descriptionLocation(String descriptionLocation) {
        this.descriptionLocation = descriptionLocation;
        return this;
    }

    public void setDescriptionLocation(String descriptionLocation) {
        this.descriptionLocation = descriptionLocation;
    }

    public Long getxCoordinate() {
        return xCoordinate;
    }

    public Location xCoordinate(Long xCoordinate) {
        this.xCoordinate = xCoordinate;
        return this;
    }

    public void setxCoordinate(Long xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Long getyCoordinate() {
        return yCoordinate;
    }

    public Location yCoordinate(Long yCoordinate) {
        this.yCoordinate = yCoordinate;
        return this;
    }

    public void setyCoordinate(Long yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getStepDuration() {
        return stepDuration;
    }

    public Location stepDuration(String stepDuration) {
        this.stepDuration = stepDuration;
        return this;
    }

    public void setStepDuration(String stepDuration) {
        this.stepDuration = stepDuration;
    }

    public Long getQrCode() {
        return qrCode;
    }

    public Location qrCode(Long qrCode) {
        this.qrCode = qrCode;
        return this;
    }

    public void setQrCode(Long qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrDescription() {
        return qrDescription;
    }

    public Location qrDescription(String qrDescription) {
        this.qrDescription = qrDescription;
        return this;
    }

    public void setQrDescription(String qrDescription) {
        this.qrDescription = qrDescription;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Location createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Location updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Duration getDuration() {
        return duration;
    }

    public Location duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Location photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Country getCountry() {
        return country;
    }

    public Location country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public Location travelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
        return this;
    }

    public void setTravelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", stepPosition=" + getStepPosition() +
            ", titleLocation='" + getTitleLocation() + "'" +
            ", descriptionLocation='" + getDescriptionLocation() + "'" +
            ", xCoordinate=" + getxCoordinate() +
            ", yCoordinate=" + getyCoordinate() +
            ", stepDuration='" + getStepDuration() + "'" +
            ", qrCode=" + getQrCode() +
            ", qrDescription='" + getQrDescription() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
