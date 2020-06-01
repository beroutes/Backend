package com.beroutess.beroutess.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.beroutess.beroutess.domain.Favorite;

import com.beroutess.beroutess.service.FavoriteService;
import com.beroutess.beroutess.web.error.CustomBeRoutesError;

@RestController
public class FavoriteController {
	
	FavoriteService favoriteService;

	public FavoriteController(FavoriteService favoriteService) {
	
		this.favoriteService = favoriteService;
	}
	
	@GetMapping(path = "/favorites")	
	List<Favorite> getFavorites(){
		return favoriteService.getFavorites();
	}
	
	@GetMapping(path = "/favorite/{id}")
	Favorite findById(@PathVariable Long id){
		if (favoriteService.findById(id).isPresent()) {
			return favoriteService.findById(id).get();
		}else {
			throw new CustomBeRoutesError("We can't find the favorite.");
		}
						
	}
	
	@PostMapping(path = "/favorite")
	Long addFavorite (@RequestBody Favorite favorite) {
		return favoriteService.addFavorite(favorite);
	}
	
	@DeleteMapping(path="/favorite/{id}")
	String deleteFavorite(@PathVariable Long id) {
		favoriteService.deleteFavorite(id);
		return(" DELETE OK");
	}
	
	@PutMapping(path = "/favorite/{id}")
	Favorite editFavorite(@PathVariable Long id, @RequestBody Favorite favorite) {
		favoriteService.editFavorite(id, favorite);
		return favorite;
	}
	
	@GetMapping(path = "/favorites/init")	
	List<Favorite> initFavorites(){
		if (favoriteService.getFavorites().size()==0) {
			return favoriteService.initFavorites();
		}else
			return favoriteService.getFavorites();
	}

}
