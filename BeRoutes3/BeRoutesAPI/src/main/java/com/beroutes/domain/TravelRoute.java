package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.beroutes.domain.enumeration.Season;

/**
 * A TravelRoute.
 */
@Entity
@Table(name = "travel_route")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "travelroute")
public class TravelRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_route")
    private String titleRoute;

    @Column(name = "destination")
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "season")
    private Season season;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "description_route")
    private String descriptionRoute;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private Duration duration;

    @OneToOne
    @JoinColumn(unique = true)
    private Category category;

    @OneToMany(mappedBy = "travelRoute")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Location> locations = new HashSet<>();

    @OneToMany(mappedBy = "travelRoute")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "travelRoute")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Valuation> valuations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("travelRoutes")
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleRoute() {
        return titleRoute;
    }

    public TravelRoute titleRoute(String titleRoute) {
        this.titleRoute = titleRoute;
        return this;
    }

    public void setTitleRoute(String titleRoute) {
        this.titleRoute = titleRoute;
    }

    public String getDestination() {
        return destination;
    }

    public TravelRoute destination(String destination) {
        this.destination = destination;
        return this;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Season getSeason() {
        return season;
    }

    public TravelRoute season(Season season) {
        this.season = season;
        return this;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Double getBudget() {
        return budget;
    }

    public TravelRoute budget(Double budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDescriptionRoute() {
        return descriptionRoute;
    }

    public TravelRoute descriptionRoute(String descriptionRoute) {
        this.descriptionRoute = descriptionRoute;
        return this;
    }

    public void setDescriptionRoute(String descriptionRoute) {
        this.descriptionRoute = descriptionRoute;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public TravelRoute createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public TravelRoute updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Duration getDuration() {
        return duration;
    }

    public TravelRoute duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Category getCategory() {
        return category;
    }

    public TravelRoute category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public TravelRoute locations(Set<Location> locations) {
        this.locations = locations;
        return this;
    }

    public TravelRoute addLocation(Location location) {
        this.locations.add(location);
        location.setTravelRoute(this);
        return this;
    }

    public TravelRoute removeLocation(Location location) {
        this.locations.remove(location);
        location.setTravelRoute(null);
        return this;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public TravelRoute photos(Set<Photo> photos) {
        this.photos = photos;
        return this;
    }

    public TravelRoute addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setTravelRoute(this);
        return this;
    }

    public TravelRoute removePhoto(Photo photo) {
        this.photos.remove(photo);
        photo.setTravelRoute(null);
        return this;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Valuation> getValuations() {
        return valuations;
    }

    public TravelRoute valuations(Set<Valuation> valuations) {
        this.valuations = valuations;
        return this;
    }

    public TravelRoute addValuation(Valuation valuation) {
        this.valuations.add(valuation);
        valuation.setTravelRoute(this);
        return this;
    }

    public TravelRoute removeValuation(Valuation valuation) {
        this.valuations.remove(valuation);
        valuation.setTravelRoute(null);
        return this;
    }

    public void setValuations(Set<Valuation> valuations) {
        this.valuations = valuations;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public TravelRoute userProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelRoute)) {
            return false;
        }
        return id != null && id.equals(((TravelRoute) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelRoute{" +
            "id=" + getId() +
            ", titleRoute='" + getTitleRoute() + "'" +
            ", destination='" + getDestination() + "'" +
            ", season='" + getSeason() + "'" +
            ", budget=" + getBudget() +
            ", descriptionRoute='" + getDescriptionRoute() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
