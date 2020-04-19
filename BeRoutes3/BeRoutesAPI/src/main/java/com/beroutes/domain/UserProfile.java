package com.beroutes.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserProfile.
 */
@Entity
@Table(name = "user_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "userprofile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone")
    private Long telephone;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "biography")
    private String biography;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private Country country;

    @OneToOne
    @JoinColumn(unique = true)
    private Photo photo;

    @OneToMany(mappedBy = "userProfile")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TravelRoute> travelRoutes = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Valuation> valuations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "user_profile_follower",
               joinColumns = @JoinColumn(name = "user_profile_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"))
    private Set<Follower> followers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfile firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfile lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserProfile email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public UserProfile telephone(Long telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public UserProfile userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public UserProfile password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfile age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBiography() {
        return biography;
    }

    public UserProfile biography(String biography) {
        this.biography = biography;
        return this;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UserProfile createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public UserProfile updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Country getCountry() {
        return country;
    }

    public UserProfile country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Photo getPhoto() {
        return photo;
    }

    public UserProfile photo(Photo photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Set<TravelRoute> getTravelRoutes() {
        return travelRoutes;
    }

    public UserProfile travelRoutes(Set<TravelRoute> travelRoutes) {
        this.travelRoutes = travelRoutes;
        return this;
    }

    public UserProfile addTravelRoute(TravelRoute travelRoute) {
        this.travelRoutes.add(travelRoute);
        travelRoute.setUserProfile(this);
        return this;
    }

    public UserProfile removeTravelRoute(TravelRoute travelRoute) {
        this.travelRoutes.remove(travelRoute);
        travelRoute.setUserProfile(null);
        return this;
    }

    public void setTravelRoutes(Set<TravelRoute> travelRoutes) {
        this.travelRoutes = travelRoutes;
    }

    public Set<Valuation> getValuations() {
        return valuations;
    }

    public UserProfile valuations(Set<Valuation> valuations) {
        this.valuations = valuations;
        return this;
    }

    public UserProfile addValuation(Valuation valuation) {
        this.valuations.add(valuation);
        valuation.setUserProfile(this);
        return this;
    }

    public UserProfile removeValuation(Valuation valuation) {
        this.valuations.remove(valuation);
        valuation.setUserProfile(null);
        return this;
    }

    public void setValuations(Set<Valuation> valuations) {
        this.valuations = valuations;
    }

    public Set<Follower> getFollowers() {
        return followers;
    }

    public UserProfile followers(Set<Follower> followers) {
        this.followers = followers;
        return this;
    }

    public UserProfile addFollower(Follower follower) {
        this.followers.add(follower);
        follower.getUserProfiles().add(this);
        return this;
    }

    public UserProfile removeFollower(Follower follower) {
        this.followers.remove(follower);
        follower.getUserProfiles().remove(this);
        return this;
    }

    public void setFollowers(Set<Follower> followers) {
        this.followers = followers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProfile)) {
            return false;
        }
        return id != null && id.equals(((UserProfile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", telephone=" + getTelephone() +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", age=" + getAge() +
            ", biography='" + getBiography() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
