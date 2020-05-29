package com.beroutess.beroutess.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beroutess.beroutess.domain.UserProfile;
import com.beroutess.beroutess.service.UserProfileService;
import com.beroutess.beroutess.web.error.CustomBeRoutesError;

@RestController
public class UserProfileController {
	
	UserProfileService  userProfileService;

	public UserProfileController(UserProfileService userProfileService) {
	
		this.userProfileService = userProfileService;
	}
	
	
	@GetMapping(path = "/usersProfiles")	
	List<UserProfile> getUsersProfiles(){
		return userProfileService.getUsersProfiles();
	}

	
	@GetMapping(path = "/userProfile/{id}")
	UserProfile findById(@PathVariable Long id){
		if (userProfileService.findById(id).isPresent()) {
			return userProfileService.findById(id).get();
		}else {
			throw new CustomBeRoutesError("We can't find the userProfile.");
		}
						
	}
	
	//Get para saber cosas de quien se ha registrado con los objetos HttpSer..Request y con Principal
	@GetMapping(path = "/usersProfiles/role")
	String getRole( HttpServletRequest request) {
		if(request.isUserInRole("ROLE_ADMIN")
				) {
			return request.getRemoteUser();}
		else {
			return "It is not the Admin user";
		}
	}
	
	
	@PostMapping(path = "/userProfile")
	Long addUserProfile (@RequestBody UserProfile userProfile) {
		return userProfileService.addUserProfile(userProfile);
	}
	
	@DeleteMapping(path="/userProfile/{id}")
	String deleteUserProfile(@PathVariable Long id) {
		userProfileService.deleteUserProfile(id);
		return(" DELETE OK");
	}
	
	@PutMapping(path = "/userProfile/{id}")
	UserProfile editUserProfile (@PathVariable Long id, @RequestBody UserProfile userProfile) {
		userProfileService.editUserProfile(id, userProfile);
		return userProfile;
	}
	
	//Get para inicializar los datos de la app
	@GetMapping(path = "/usersProfiles/init")	
	List<UserProfile> getInitUserProfiles(){
		
		List<UserProfile> theList = new ArrayList<UserProfile>();
		theList = userProfileService.initUserProfiles();
		int a = theList.size();
		
		if(a==0) {
			return	userProfileService.initUserProfiles();
			
		}else
		
		return userProfileService.getUsersProfiles();
	}

}
