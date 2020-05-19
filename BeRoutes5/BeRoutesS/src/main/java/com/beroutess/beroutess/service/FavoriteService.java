package com.beroutess.beroutess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.beroutess.beroutess.domain.Favorite;

import com.beroutess.beroutess.repository.FavoriteRepository;

@Service
public class FavoriteService {
	//Llamamos a la información del repositorio
	
		FavoriteRepository favoriteRepository;
		
		   //Inicializamos con un constructor
		public FavoriteService(FavoriteRepository favoriteRepository) {
		
			this.favoriteRepository = favoriteRepository;
		}
		
		
		///Los métodos del servicio
		
		public List<Favorite>getFavorites(){
			return favoriteRepository.findAll();
		}
		
		public Optional<Favorite> findById(Long id) {
			return favoriteRepository.findById(id);
		}

		public Long addFavorite (Favorite favorite) {
			Favorite favoriteSaved = favoriteRepository.save(favorite);
			return favoriteSaved.getId();		
		}
		
		public Long deleteFavorite(Long id) {
			favoriteRepository.delete(favoriteRepository.getOne(id));
			return favoriteRepository.count();
		}
		
		public Favorite editFavorite(Long id, Favorite favorite) {
			Favorite favoriteSaved = favoriteRepository.getOne(id);
			
			favoriteSaved.setLike(favorite.getLike());
			favoriteSaved.setNotLike(favorite.getNotLike());
	
			favoriteRepository.save(favoriteSaved);
			return favoriteSaved;
		}
		
		
}
