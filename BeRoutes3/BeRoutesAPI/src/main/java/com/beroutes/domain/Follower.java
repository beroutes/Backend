package com.beroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Follower.
 */
@Entity
@Table(name = "follower")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "follower")
public class Follower implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accepted")
    private Boolean accepted;

    @ManyToMany(mappedBy = "followers")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<UserProfile> userProfiles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public Follower accepted(Boolean accepted) {
        this.accepted = accepted;
        return this;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public Follower userProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
        return this;
    }

    public Follower addUserProfile(UserProfile userProfile) {
        this.userProfiles.add(userProfile);
        userProfile.getFollowers().add(this);
        return this;
    }

    public Follower removeUserProfile(UserProfile userProfile) {
        this.userProfiles.remove(userProfile);
        userProfile.getFollowers().remove(this);
        return this;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Follower)) {
            return false;
        }
        return id != null && id.equals(((Follower) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Follower{" +
            "id=" + getId() +
            ", accepted='" + isAccepted() + "'" +
            "}";
    }
}
