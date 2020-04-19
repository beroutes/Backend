package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category")
    private String nameCategory;

    @Column(name = "cheap")
    private Boolean cheap;

    @Column(name = "luxury")
    private Boolean luxury;

    @Column(name = "lonely")
    private Boolean lonely;

    @Column(name = "friends")
    private Boolean friends;

    @Column(name = "romantic")
    private Boolean romantic;

    @Column(name = "kids")
    private Boolean kids;

    @Column(name = "sport")
    private Boolean sport;

    @Column(name = "relaxation")
    private Boolean relaxation;

    @Column(name = "art")
    private Boolean art;

    @Column(name = "food")
    private Boolean food;

    @Column(name = "nature")
    private Boolean nature;

    @Column(name = "city")
    private Boolean city;

    @OneToOne(mappedBy = "category")
    @JsonIgnore
    private TravelRoute travelRoute;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public Category nameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
        return this;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Boolean isCheap() {
        return cheap;
    }

    public Category cheap(Boolean cheap) {
        this.cheap = cheap;
        return this;
    }

    public void setCheap(Boolean cheap) {
        this.cheap = cheap;
    }

    public Boolean isLuxury() {
        return luxury;
    }

    public Category luxury(Boolean luxury) {
        this.luxury = luxury;
        return this;
    }

    public void setLuxury(Boolean luxury) {
        this.luxury = luxury;
    }

    public Boolean isLonely() {
        return lonely;
    }

    public Category lonely(Boolean lonely) {
        this.lonely = lonely;
        return this;
    }

    public void setLonely(Boolean lonely) {
        this.lonely = lonely;
    }

    public Boolean isFriends() {
        return friends;
    }

    public Category friends(Boolean friends) {
        this.friends = friends;
        return this;
    }

    public void setFriends(Boolean friends) {
        this.friends = friends;
    }

    public Boolean isRomantic() {
        return romantic;
    }

    public Category romantic(Boolean romantic) {
        this.romantic = romantic;
        return this;
    }

    public void setRomantic(Boolean romantic) {
        this.romantic = romantic;
    }

    public Boolean isKids() {
        return kids;
    }

    public Category kids(Boolean kids) {
        this.kids = kids;
        return this;
    }

    public void setKids(Boolean kids) {
        this.kids = kids;
    }

    public Boolean isSport() {
        return sport;
    }

    public Category sport(Boolean sport) {
        this.sport = sport;
        return this;
    }

    public void setSport(Boolean sport) {
        this.sport = sport;
    }

    public Boolean isRelaxation() {
        return relaxation;
    }

    public Category relaxation(Boolean relaxation) {
        this.relaxation = relaxation;
        return this;
    }

    public void setRelaxation(Boolean relaxation) {
        this.relaxation = relaxation;
    }

    public Boolean isArt() {
        return art;
    }

    public Category art(Boolean art) {
        this.art = art;
        return this;
    }

    public void setArt(Boolean art) {
        this.art = art;
    }

    public Boolean isFood() {
        return food;
    }

    public Category food(Boolean food) {
        this.food = food;
        return this;
    }

    public void setFood(Boolean food) {
        this.food = food;
    }

    public Boolean isNature() {
        return nature;
    }

    public Category nature(Boolean nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(Boolean nature) {
        this.nature = nature;
    }

    public Boolean isCity() {
        return city;
    }

    public Category city(Boolean city) {
        this.city = city;
        return this;
    }

    public void setCity(Boolean city) {
        this.city = city;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public Category travelRoute(TravelRoute travelRoute) {
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
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", nameCategory='" + getNameCategory() + "'" +
            ", cheap='" + isCheap() + "'" +
            ", luxury='" + isLuxury() + "'" +
            ", lonely='" + isLonely() + "'" +
            ", friends='" + isFriends() + "'" +
            ", romantic='" + isRomantic() + "'" +
            ", kids='" + isKids() + "'" +
            ", sport='" + isSport() + "'" +
            ", relaxation='" + isRelaxation() + "'" +
            ", art='" + isArt() + "'" +
            ", food='" + isFood() + "'" +
            ", nature='" + isNature() + "'" +
            ", city='" + isCity() + "'" +
            "}";
    }
}
