package com.beroutess.beroutess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.beroutess.beroutess.domain.UserProfile;
import com.beroutess.beroutess.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	UserProfileRepository userProfileRepository;

	public UserProfileService(UserProfileRepository userProfileRepository) {
	
		this.userProfileRepository = userProfileRepository;
	}
	
	
	
	public List<UserProfile>getUsersProfiles(){
		return userProfileRepository.findAll();
	}
	
	public Optional<UserProfile> findById(Long id) {
		return userProfileRepository.findById(id);
	}

	public Long addUserProfile(UserProfile userProfile) {
		UserProfile userProfileSaved = userProfileRepository.save(userProfile);
		return userProfileSaved.getId();		
	}
	
	public Long deleteUserProfile(Long id) {
		userProfileRepository.delete(userProfileRepository.getOne(id));
		return userProfileRepository.count();
	}
	
	public UserProfile editUserProfile(Long id, UserProfile userProfile) {
		UserProfile userProfileSaved = userProfileRepository.getOne(id);
		userProfileSaved.setFirstName(userProfile.getFirstName());
		userProfileSaved.setLastName(userProfile.getLastName());
		userProfileSaved.setEmail(userProfile.getEmail());
		userProfileSaved.setTelephone(userProfile.getTelephone());
		userProfileSaved.setUserName(userProfile.getUserName());
		userProfileSaved.setPassword(userProfile.getPassword());
		userProfileSaved.setAge(userProfile.getAge());
		userProfileSaved.setBiography(userProfile.getBiography());
		userProfileSaved.setCreatedAt(userProfile.getCreatedAt());
		userProfileSaved.setUpdatedAt(userProfile.getUpdatedAt());
		userProfileSaved.setFollower(userProfile.getFollower());
		userProfileSaved.setFollowed(userProfile.getFollowed());
		
		//userProfileSaved.setCountry(userProfile.getCountry());
		//userProfileSaved.setPhoto(userProfile.getPhoto());
		userProfileSaved.setTravelRoutes(userProfile.getTravelRoutes());
		//userProfileSaved.setValuations(userProfile.getValuations());
	    //userProfileSaved.setFollowings(userProfile.getFollowings());
		userProfileSaved.setFavorites(userProfile.getFavorites());
		
		
		
		userProfileRepository.save(userProfileSaved);
		return userProfileSaved;
		
	}
	
	///Metodos para icializar UserProfile
	
		public List<UserProfile> initUserProfiles() {
			
			UserProfile newUserProfile0 = new UserProfile();
			
			newUserProfile0.setFirstName("Anna");
			newUserProfile0.setLastName("Times");
			newUserProfile0.setEmail("annatimes@gmail.com");
			newUserProfile0.setTelephone(555555555);
			newUserProfile0.setUserName("annatravaller");
			newUserProfile0.setPassword("12345");
			newUserProfile0.setAge(25);
			newUserProfile0.setBiography("Estoy empezando a viajar, poco puedo contar");
			newUserProfile0.setCreatedAt(null);
			newUserProfile0.setUpdatedAt(null);
			newUserProfile0.setFollower(0);
			newUserProfile0.setFollowed(0);
			
					
			userProfileRepository.save(newUserProfile0);
			
			UserProfile newUserProfile1 = new UserProfile();
			
			newUserProfile1.setFirstName("Erik");
			newUserProfile1.setLastName("Olson");
			newUserProfile1.setEmail("erikolson@gmail.com");
			newUserProfile1.setTelephone(555555555);
			newUserProfile1.setUserName("worldtraveller");
			newUserProfile1.setPassword("12345");
			newUserProfile1.setAge(38);
			newUserProfile1.setBiography("VOlando voy volando vengo por el camino yo me entretengo");
			newUserProfile1.setCreatedAt(null);
			newUserProfile1.setUpdatedAt(null);
			newUserProfile1.setFollower(0);
			newUserProfile1.setFollowed(0);
			
					
			userProfileRepository.save(newUserProfile1);
			
			return userProfileRepository.findAll();
		}

}
