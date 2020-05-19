package com.beroutess.beroutess.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.beroutess.beroutess.domain.Photo;

import com.beroutess.beroutess.service.PhotoService;
import com.beroutess.beroutess.web.error.CustomBeRoutesError;

@RestController
public class PhotoController {
	
	PhotoService photoService;

	public PhotoController(PhotoService photoService) {
	
		this.photoService = photoService;
	}
	
	@GetMapping(path = "/photos")	
	List<Photo> getPhotos(){
		return photoService.getPhotos();
	}
	
	@GetMapping(path = "/photo/{id}")
	Photo findById(@PathVariable Long id){
		if (photoService.findById(id).isPresent()) {
			return photoService.findById(id).get();
		}else {
			throw new CustomBeRoutesError("We can't find the photo.");
		}
						
	}
	
	@PostMapping(path = "/photo")
	Long addPhoto(@RequestBody Photo photo) {
		return photoService.addPhoto(photo);
	}
	
	@DeleteMapping(path="/photo/{id}")
	String deletePhoto(@PathVariable Long id) {
		photoService.deletePhoto(id);
		return(" DELETE OK");
	}
	
	@PutMapping(path = "/photo/{id}")
	Photo editPhoto(@PathVariable Long id, @RequestBody Photo photo) {
		photoService.editPhoto(id, photo);
		return photo;
	}
	
	@GetMapping(path = "/photos/init")	
	List<Photo> initPhotos(){
		return photoService.initPhotos();
	}

}
