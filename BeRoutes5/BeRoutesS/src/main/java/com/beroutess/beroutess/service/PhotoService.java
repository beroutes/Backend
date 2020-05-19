package com.beroutess.beroutess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



import com.beroutess.beroutess.domain.Photo;

import com.beroutess.beroutess.repository.PhotoRepository;

@Service
public class PhotoService {
	
	PhotoRepository photoRepository;

	public PhotoService(PhotoRepository photoRepository) {
	
		this.photoRepository = photoRepository;
	}
	
	///Los métodos del servicio
	
			public List<Photo>getPhotos(){
				return photoRepository.findAll();
			}
			
			public Optional<Photo> findById(Long id) {
				return photoRepository.findById(id);
			}
			public Long addPhoto (Photo photo) {
				Photo photoSaved = photoRepository.save(photo);
				return photoSaved.getId();		
			}
			
			public Long deletePhoto(Long id) {
				photoRepository.delete(photoRepository.getOne(id));
				return photoRepository.count();
			}
			
			public Photo editPhoto(Long id, Photo photo) {
				Photo photoSaved = photoRepository.getOne(id);
				
				photoSaved.setTitlePhoto(photo.getTitlePhoto());
				photoSaved.setDescriptionPhoto(photo.getDescriptionPhoto());
				photoSaved.setPhotoMain(photo.getPhotoMain());
				photoSaved.setPhotoMap(photo.getPhotoMap());
				photoSaved.setPhotoLocation(photo.getPhotoLocation());
				photoSaved.setPhotoProfile(photo.getPhotoProfile());
				photoSaved.setUrlPhoto(photo.getUrlPhoto());
				photoSaved.setCodePhoto(photo.getCodePhoto());
				photoSaved.setImageRoute(photo.getImageRoute());
				photoSaved.setImageRouteContentType(photo.getImageRouteContentType());
				
				photoRepository.save(photoSaved);
				return photoSaved;
			}
			
			public List<Photo> initPhotos() {
				
			    
				// Photos que inicializamos
				 
				Photo newPhoto0 = new Photo();
				
				newPhoto0.setTitlePhoto("ian-schneider-jk8rarn6lmw-unsplash.jpg");
				newPhoto0.setDescriptionPhoto("San Francisco");
				newPhoto0.setPhotoMain(true);
				newPhoto0.setPhotoMap(false);
				newPhoto0.setPhotoLocation(false);
				newPhoto0.setPhotoProfile(false);
				newPhoto0.setUrlPhoto(null);
				newPhoto0.setCodePhoto(0);
				newPhoto0.setImageRoute(null);
				newPhoto0.setImageRouteContentType("");
				
				photoRepository.save(newPhoto0);
				
				Photo newPhoto1 = new Photo();
				
				newPhoto1.setTitlePhoto("mesut-kaya-eOcyhe5-9sQ-unsplash.jpg");
				newPhoto1.setDescriptionPhoto("Capadocia");
				newPhoto1.setPhotoMain(true);
				newPhoto1.setPhotoMap(false);
				newPhoto1.setPhotoLocation(false);
				newPhoto1.setPhotoProfile(false);
				newPhoto1.setUrlPhoto(null);
				newPhoto1.setCodePhoto(0);
				newPhoto1.setImageRoute(null);
				newPhoto1.setImageRouteContentType("");
				
				photoRepository.save(newPhoto1);
	
				Photo newPhoto2 = new Photo();
				
				newPhoto2.setTitlePhoto("luca-bravo-O453M2Liufs-unsplash.jpg");
				newPhoto2.setDescriptionPhoto("Greenland");
				newPhoto2.setPhotoMain(true);
				newPhoto2.setPhotoMap(false);
				newPhoto2.setPhotoLocation(false);
				newPhoto2.setPhotoProfile(false);
				newPhoto2.setUrlPhoto(null);
				newPhoto2.setCodePhoto(0);
				newPhoto2.setImageRoute(null);
				newPhoto2.setImageRouteContentType("");
				
				photoRepository.save(newPhoto2);
				
				Photo newPhoto3 = new Photo();
				
				newPhoto3.setTitlePhoto("frida-aguilar-estrada-fJeJHpV9mmk-unsplash.jpg");
				newPhoto3.setDescriptionPhoto("Morocco");
				newPhoto3.setPhotoMain(true);
				newPhoto3.setPhotoMap(false);
				newPhoto3.setPhotoLocation(false);
				newPhoto3.setPhotoProfile(false);
				newPhoto3.setUrlPhoto(null);
				newPhoto3.setCodePhoto(0);
				newPhoto3.setImageRoute(null);
				newPhoto3.setImageRouteContentType("");
				
				photoRepository.save(newPhoto3);
				
				Photo newPhoto4 = new Photo();
				
				newPhoto4.setTitlePhoto("GoogleViajes.JPG");
				newPhoto4.setDescriptionPhoto("RutaGoogle");
				newPhoto4.setPhotoMain(false);
				newPhoto4.setPhotoMap(true);
				newPhoto4.setPhotoLocation(false);
				newPhoto4.setPhotoProfile(false);
				newPhoto4.setUrlPhoto(null);
				newPhoto4.setCodePhoto(0);
				newPhoto4.setImageRoute(null);
				newPhoto4.setImageRouteContentType("");
				
				photoRepository.save(newPhoto4);
				
				Photo newPhoto5 = new Photo();
				
				newPhoto5.setTitlePhoto("maximillian-conacher-sPpe2D7VbpM-unsplash.jpg");
				newPhoto5.setDescriptionPhoto("San Francisco location");
				newPhoto5.setPhotoMain(false);
				newPhoto5.setPhotoMap(false);
				newPhoto5.setPhotoLocation(true);
				newPhoto5.setPhotoProfile(false);
				newPhoto5.setUrlPhoto(null);
				newPhoto5.setCodePhoto(0);
				newPhoto5.setImageRoute(null);
				newPhoto5.setImageRouteContentType("");
				
				photoRepository.save(newPhoto5);
				
		       Photo newPhoto6 = new Photo();
				
		       newPhoto6.setTitlePhoto("guilherme-stecanella-_dH-oQF9w-Y-unsplash.jpg");
		       newPhoto6.setDescriptionPhoto("PhotoProfile");
		       newPhoto6.setPhotoMain(false);
		       newPhoto6.setPhotoMap(false);
		       newPhoto6.setPhotoLocation(false);
		       newPhoto6.setPhotoProfile(true);
		       newPhoto6.setUrlPhoto(null);
		       newPhoto6.setCodePhoto(0);
		       newPhoto6.setImageRoute(null);
		       newPhoto6.setImageRouteContentType("");
				
				photoRepository.save(newPhoto6);
	
				return photoRepository.findAll();
			}
}
