package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Valuation.
 */
@Entity
@Table(name = "valuation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "valuation")
public class Valuation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JsonIgnoreProperties("valuations")
    private TravelRoute travelRoute;

    @ManyToOne
    @JsonIgnoreProperties("valuations")
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public Valuation comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Valuation rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public Valuation travelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
        return this;
    }

    public void setTravelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Valuation userProfile(UserProfile userProfile) {
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
        if (!(o instanceof Valuation)) {
            return false;
        }
        return id != null && id.equals(((Valuation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Valuation{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", rating=" + getRating() +
            "}";
    }
}
