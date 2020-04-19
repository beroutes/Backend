package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Duration.
 */
@Entity
@Table(name = "duration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "duration")
public class Duration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_duration")
    private String descriptionDuration;

    @Column(name = "minutes")
    private Long minutes;

    @Column(name = "hours")
    private Long hours;

    @Column(name = "days")
    private Long days;

    @Column(name = "weeks")
    private Long weeks;

    @Column(name = "years")
    private Long years;

    @OneToOne(mappedBy = "duration")
    @JsonIgnore
    private TravelRoute travelRoute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionDuration() {
        return descriptionDuration;
    }

    public Duration descriptionDuration(String descriptionDuration) {
        this.descriptionDuration = descriptionDuration;
        return this;
    }

    public void setDescriptionDuration(String descriptionDuration) {
        this.descriptionDuration = descriptionDuration;
    }

    public Long getMinutes() {
        return minutes;
    }

    public Duration minutes(Long minutes) {
        this.minutes = minutes;
        return this;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getHours() {
        return hours;
    }

    public Duration hours(Long hours) {
        this.hours = hours;
        return this;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getDays() {
        return days;
    }

    public Duration days(Long days) {
        this.days = days;
        return this;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Long getWeeks() {
        return weeks;
    }

    public Duration weeks(Long weeks) {
        this.weeks = weeks;
        return this;
    }

    public void setWeeks(Long weeks) {
        this.weeks = weeks;
    }

    public Long getYears() {
        return years;
    }

    public Duration years(Long years) {
        this.years = years;
        return this;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public Duration travelRoute(TravelRoute travelRoute) {
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
        if (!(o instanceof Duration)) {
            return false;
        }
        return id != null && id.equals(((Duration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Duration{" +
            "id=" + getId() +
            ", descriptionDuration='" + getDescriptionDuration() + "'" +
            ", minutes=" + getMinutes() +
            ", hours=" + getHours() +
            ", days=" + getDays() +
            ", weeks=" + getWeeks() +
            ", years=" + getYears() +
            "}";
    }
}
