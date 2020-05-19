package com.beroutess.beroutess.domain;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;






import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "following")
public class Following {
	@Id
	@GeneratedValue
	Long id;
	
    @Column(name = "follow")
    Boolean follow;

    @Column(name = "accepted")
    Boolean accepted;

    @Column(name = "user_follower")
    Long userFollower;

    @Column(name = "user_followed")
    Long userFollowed;

    //No tengo clara esta relación.//o
    // @OneToMany(mappedBy = "following")
    // List<UserProfile> userProfiles;

    /*
    
    //o
    @ManyToOne
    @JsonManagedReference
    UserProfile userProfile;
    */
    

	public Long getId() {
		return id;
	}

	public Boolean getFollow() {
		return follow;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public Long getUserFollower() {
		return userFollower;
	}

	public Long getUserFollowed() {
		return userFollowed;
	}

	
	/*
	public UserProfile getUserProfile() {
		return userProfile;
	}
	
*/
	public void setId(Long id) {
		this.id = id;
	}

	public void setFollow(Boolean follow) {
		this.follow = follow;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public void setUserFollower(Long userFollower) {
		this.userFollower = userFollower;
	}

	public void setUserFollowed(Long userFollowed) {
		this.userFollowed = userFollowed;
	}

	/*
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
    */
    

}
