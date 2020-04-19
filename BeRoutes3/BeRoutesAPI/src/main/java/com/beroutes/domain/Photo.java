package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Photo.
 */
@Entity
@Table(name = "photo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "photo")
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_photo")
    private String titlePhoto;

    @Column(name = "description_photo")
    private String descriptionPhoto;

    @Column(name = "url_photo")
    private String urlPhoto;

    @OneToOne(mappedBy = "photo")
    @JsonIgnore
    private Location location;

    @OneToOne(mappedBy = "photo")
    @JsonIgnore
    private UserProfile userProfile;

    @ManyToOne
    @JsonIgnoreProperties("photos")
    private TravelRoute travelRoute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public Photo titlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
        return this;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getDescriptionPhoto() {
        return descriptionPhoto;
    }

    public Photo descriptionPhoto(String descriptionPhoto) {
        this.descriptionPhoto = descriptionPhoto;
        return this;
    }

    public void setDescriptionPhoto(String descriptionPhoto) {
        this.descriptionPhoto = descriptionPhoto;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public Photo urlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
        return this;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Location getLocation() {
        return location;
    }

    public Photo location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Photo userProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public Photo travelRoute(TravelRoute travelRoute) {
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
        if (!(o instanceof Photo)) {
            return false;
        }
        return id != null && id.equals(((Photo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Photo{" +
            "id=" + getId() +
            ", titlePhoto='" + getTitlePhoto() + "'" +
            ", descriptionPhoto='" + getDescriptionPhoto() + "'" +
            ", urlPhoto='" + getUrlPhoto() + "'" +
            "}";
    }
}
