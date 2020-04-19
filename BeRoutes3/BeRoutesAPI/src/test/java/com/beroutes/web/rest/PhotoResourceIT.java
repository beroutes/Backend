package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.Photo;
import com.beroutes.repository.PhotoRepository;
import com.beroutes.repository.search.PhotoSearchRepository;
import com.beroutes.service.PhotoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PhotoResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PhotoResourceIT {

    private static final String DEFAULT_TITLE_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_PHOTO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_PHOTO = "BBBBBBBBBB";

    private static final String DEFAULT_URL_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_URL_PHOTO = "BBBBBBBBBB";

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoService photoService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.PhotoSearchRepositoryMockConfiguration
     */
    @Autowired
    private PhotoSearchRepository mockPhotoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhotoMockMvc;

    private Photo photo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Photo createEntity(EntityManager em) {
        Photo photo = new Photo()
            .titlePhoto(DEFAULT_TITLE_PHOTO)
            .descriptionPhoto(DEFAULT_DESCRIPTION_PHOTO)
            .urlPhoto(DEFAULT_URL_PHOTO);
        return photo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Photo createUpdatedEntity(EntityManager em) {
        Photo photo = new Photo()
            .titlePhoto(UPDATED_TITLE_PHOTO)
            .descriptionPhoto(UPDATED_DESCRIPTION_PHOTO)
            .urlPhoto(UPDATED_URL_PHOTO);
        return photo;
    }

    @BeforeEach
    public void initTest() {
        photo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhoto() throws Exception {
        int databaseSizeBeforeCreate = photoRepository.findAll().size();

        // Create the Photo
        restPhotoMockMvc.perform(post("/api/photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(photo)))
            .andExpect(status().isCreated());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeCreate + 1);
        Photo testPhoto = photoList.get(photoList.size() - 1);
        assertThat(testPhoto.getTitlePhoto()).isEqualTo(DEFAULT_TITLE_PHOTO);
        assertThat(testPhoto.getDescriptionPhoto()).isEqualTo(DEFAULT_DESCRIPTION_PHOTO);
        assertThat(testPhoto.getUrlPhoto()).isEqualTo(DEFAULT_URL_PHOTO);

        // Validate the Photo in Elasticsearch
        verify(mockPhotoSearchRepository, times(1)).save(testPhoto);
    }

    @Test
    @Transactional
    public void createPhotoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = photoRepository.findAll().size();

        // Create the Photo with an existing ID
        photo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhotoMockMvc.perform(post("/api/photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(photo)))
            .andExpect(status().isBadRequest());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeCreate);

        // Validate the Photo in Elasticsearch
        verify(mockPhotoSearchRepository, times(0)).save(photo);
    }


    @Test
    @Transactional
    public void getAllPhotos() throws Exception {
        // Initialize the database
        photoRepository.saveAndFlush(photo);

        // Get all the photoList
        restPhotoMockMvc.perform(get("/api/photos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(photo.getId().intValue())))
            .andExpect(jsonPath("$.[*].titlePhoto").value(hasItem(DEFAULT_TITLE_PHOTO)))
            .andExpect(jsonPath("$.[*].descriptionPhoto").value(hasItem(DEFAULT_DESCRIPTION_PHOTO)))
            .andExpect(jsonPath("$.[*].urlPhoto").value(hasItem(DEFAULT_URL_PHOTO)));
    }
    
    @Test
    @Transactional
    public void getPhoto() throws Exception {
        // Initialize the database
        photoRepository.saveAndFlush(photo);

        // Get the photo
        restPhotoMockMvc.perform(get("/api/photos/{id}", photo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(photo.getId().intValue()))
            .andExpect(jsonPath("$.titlePhoto").value(DEFAULT_TITLE_PHOTO))
            .andExpect(jsonPath("$.descriptionPhoto").value(DEFAULT_DESCRIPTION_PHOTO))
            .andExpect(jsonPath("$.urlPhoto").value(DEFAULT_URL_PHOTO));
    }

    @Test
    @Transactional
    public void getNonExistingPhoto() throws Exception {
        // Get the photo
        restPhotoMockMvc.perform(get("/api/photos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhoto() throws Exception {
        // Initialize the database
        photoService.save(photo);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockPhotoSearchRepository);

        int databaseSizeBeforeUpdate = photoRepository.findAll().size();

        // Update the photo
        Photo updatedPhoto = photoRepository.findById(photo.getId()).get();
        // Disconnect from session so that the updates on updatedPhoto are not directly saved in db
        em.detach(updatedPhoto);
        updatedPhoto
            .titlePhoto(UPDATED_TITLE_PHOTO)
            .descriptionPhoto(UPDATED_DESCRIPTION_PHOTO)
            .urlPhoto(UPDATED_URL_PHOTO);

        restPhotoMockMvc.perform(put("/api/photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPhoto)))
            .andExpect(status().isOk());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeUpdate);
        Photo testPhoto = photoList.get(photoList.size() - 1);
        assertThat(testPhoto.getTitlePhoto()).isEqualTo(UPDATED_TITLE_PHOTO);
        assertThat(testPhoto.getDescriptionPhoto()).isEqualTo(UPDATED_DESCRIPTION_PHOTO);
        assertThat(testPhoto.getUrlPhoto()).isEqualTo(UPDATED_URL_PHOTO);

        // Validate the Photo in Elasticsearch
        verify(mockPhotoSearchRepository, times(1)).save(testPhoto);
    }

    @Test
    @Transactional
    public void updateNonExistingPhoto() throws Exception {
        int databaseSizeBeforeUpdate = photoRepository.findAll().size();

        // Create the Photo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhotoMockMvc.perform(put("/api/photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(photo)))
            .andExpect(status().isBadRequest());

        // Validate the Photo in the database
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Photo in Elasticsearch
        verify(mockPhotoSearchRepository, times(0)).save(photo);
    }

    @Test
    @Transactional
    public void deletePhoto() throws Exception {
        // Initialize the database
        photoService.save(photo);

        int databaseSizeBeforeDelete = photoRepository.findAll().size();

        // Delete the photo
        restPhotoMockMvc.perform(delete("/api/photos/{id}", photo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Photo> photoList = photoRepository.findAll();
        assertThat(photoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Photo in Elasticsearch
        verify(mockPhotoSearchRepository, times(1)).deleteById(photo.getId());
    }

    @Test
    @Transactional
    public void searchPhoto() throws Exception {
        // Initialize the database
        photoService.save(photo);
        when(mockPhotoSearchRepository.search(queryStringQuery("id:" + photo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(photo), PageRequest.of(0, 1), 1));
        // Search the photo
        restPhotoMockMvc.perform(get("/api/_search/photos?query=id:" + photo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(photo.getId().intValue())))
            .andExpect(jsonPath("$.[*].titlePhoto").value(hasItem(DEFAULT_TITLE_PHOTO)))
            .andExpect(jsonPath("$.[*].descriptionPhoto").value(hasItem(DEFAULT_DESCRIPTION_PHOTO)))
            .andExpect(jsonPath("$.[*].urlPhoto").value(hasItem(DEFAULT_URL_PHOTO)));
    }
}
