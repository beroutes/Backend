package com.beroutes.web.rest;

import com.beroutes.BeRoutesApiApp;
import com.beroutes.domain.Category;
import com.beroutes.repository.CategoryRepository;
import com.beroutes.repository.search.CategorySearchRepository;
import com.beroutes.service.CategoryService;

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
 * Integration tests for the {@link CategoryResource} REST controller.
 */
@SpringBootTest(classes = BeRoutesApiApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategoryResourceIT {

    private static final String DEFAULT_NAME_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CATEGORY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CHEAP = false;
    private static final Boolean UPDATED_CHEAP = true;

    private static final Boolean DEFAULT_LUXURY = false;
    private static final Boolean UPDATED_LUXURY = true;

    private static final Boolean DEFAULT_LONELY = false;
    private static final Boolean UPDATED_LONELY = true;

    private static final Boolean DEFAULT_FRIENDS = false;
    private static final Boolean UPDATED_FRIENDS = true;

    private static final Boolean DEFAULT_ROMANTIC = false;
    private static final Boolean UPDATED_ROMANTIC = true;

    private static final Boolean DEFAULT_KIDS = false;
    private static final Boolean UPDATED_KIDS = true;

    private static final Boolean DEFAULT_SPORT = false;
    private static final Boolean UPDATED_SPORT = true;

    private static final Boolean DEFAULT_RELAXATION = false;
    private static final Boolean UPDATED_RELAXATION = true;

    private static final Boolean DEFAULT_ART = false;
    private static final Boolean UPDATED_ART = true;

    private static final Boolean DEFAULT_FOOD = false;
    private static final Boolean UPDATED_FOOD = true;

    private static final Boolean DEFAULT_NATURE = false;
    private static final Boolean UPDATED_NATURE = true;

    private static final Boolean DEFAULT_CITY = false;
    private static final Boolean UPDATED_CITY = true;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    /**
     * This repository is mocked in the com.beroutes.repository.search test package.
     *
     * @see com.beroutes.repository.search.CategorySearchRepositoryMockConfiguration
     */
    @Autowired
    private CategorySearchRepository mockCategorySearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryMockMvc;

    private Category category;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category createEntity(EntityManager em) {
        Category category = new Category()
            .nameCategory(DEFAULT_NAME_CATEGORY)
            .cheap(DEFAULT_CHEAP)
            .luxury(DEFAULT_LUXURY)
            .lonely(DEFAULT_LONELY)
            .friends(DEFAULT_FRIENDS)
            .romantic(DEFAULT_ROMANTIC)
            .kids(DEFAULT_KIDS)
            .sport(DEFAULT_SPORT)
            .relaxation(DEFAULT_RELAXATION)
            .art(DEFAULT_ART)
            .food(DEFAULT_FOOD)
            .nature(DEFAULT_NATURE)
            .city(DEFAULT_CITY);
        return category;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category createUpdatedEntity(EntityManager em) {
        Category category = new Category()
            .nameCategory(UPDATED_NAME_CATEGORY)
            .cheap(UPDATED_CHEAP)
            .luxury(UPDATED_LUXURY)
            .lonely(UPDATED_LONELY)
            .friends(UPDATED_FRIENDS)
            .romantic(UPDATED_ROMANTIC)
            .kids(UPDATED_KIDS)
            .sport(UPDATED_SPORT)
            .relaxation(UPDATED_RELAXATION)
            .art(UPDATED_ART)
            .food(UPDATED_FOOD)
            .nature(UPDATED_NATURE)
            .city(UPDATED_CITY);
        return category;
    }

    @BeforeEach
    public void initTest() {
        category = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(category)))
            .andExpect(status().isCreated());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate + 1);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getNameCategory()).isEqualTo(DEFAULT_NAME_CATEGORY);
        assertThat(testCategory.isCheap()).isEqualTo(DEFAULT_CHEAP);
        assertThat(testCategory.isLuxury()).isEqualTo(DEFAULT_LUXURY);
        assertThat(testCategory.isLonely()).isEqualTo(DEFAULT_LONELY);
        assertThat(testCategory.isFriends()).isEqualTo(DEFAULT_FRIENDS);
        assertThat(testCategory.isRomantic()).isEqualTo(DEFAULT_ROMANTIC);
        assertThat(testCategory.isKids()).isEqualTo(DEFAULT_KIDS);
        assertThat(testCategory.isSport()).isEqualTo(DEFAULT_SPORT);
        assertThat(testCategory.isRelaxation()).isEqualTo(DEFAULT_RELAXATION);
        assertThat(testCategory.isArt()).isEqualTo(DEFAULT_ART);
        assertThat(testCategory.isFood()).isEqualTo(DEFAULT_FOOD);
        assertThat(testCategory.isNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testCategory.isCity()).isEqualTo(DEFAULT_CITY);

        // Validate the Category in Elasticsearch
        verify(mockCategorySearchRepository, times(1)).save(testCategory);
    }

    @Test
    @Transactional
    public void createCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category with an existing ID
        category.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(category)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate);

        // Validate the Category in Elasticsearch
        verify(mockCategorySearchRepository, times(0)).save(category);
    }


    @Test
    @Transactional
    public void getAllCategories() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList
        restCategoryMockMvc.perform(get("/api/categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameCategory").value(hasItem(DEFAULT_NAME_CATEGORY)))
            .andExpect(jsonPath("$.[*].cheap").value(hasItem(DEFAULT_CHEAP.booleanValue())))
            .andExpect(jsonPath("$.[*].luxury").value(hasItem(DEFAULT_LUXURY.booleanValue())))
            .andExpect(jsonPath("$.[*].lonely").value(hasItem(DEFAULT_LONELY.booleanValue())))
            .andExpect(jsonPath("$.[*].friends").value(hasItem(DEFAULT_FRIENDS.booleanValue())))
            .andExpect(jsonPath("$.[*].romantic").value(hasItem(DEFAULT_ROMANTIC.booleanValue())))
            .andExpect(jsonPath("$.[*].kids").value(hasItem(DEFAULT_KIDS.booleanValue())))
            .andExpect(jsonPath("$.[*].sport").value(hasItem(DEFAULT_SPORT.booleanValue())))
            .andExpect(jsonPath("$.[*].relaxation").value(hasItem(DEFAULT_RELAXATION.booleanValue())))
            .andExpect(jsonPath("$.[*].art").value(hasItem(DEFAULT_ART.booleanValue())))
            .andExpect(jsonPath("$.[*].food").value(hasItem(DEFAULT_FOOD.booleanValue())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.booleanValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(category.getId().intValue()))
            .andExpect(jsonPath("$.nameCategory").value(DEFAULT_NAME_CATEGORY))
            .andExpect(jsonPath("$.cheap").value(DEFAULT_CHEAP.booleanValue()))
            .andExpect(jsonPath("$.luxury").value(DEFAULT_LUXURY.booleanValue()))
            .andExpect(jsonPath("$.lonely").value(DEFAULT_LONELY.booleanValue()))
            .andExpect(jsonPath("$.friends").value(DEFAULT_FRIENDS.booleanValue()))
            .andExpect(jsonPath("$.romantic").value(DEFAULT_ROMANTIC.booleanValue()))
            .andExpect(jsonPath("$.kids").value(DEFAULT_KIDS.booleanValue()))
            .andExpect(jsonPath("$.sport").value(DEFAULT_SPORT.booleanValue()))
            .andExpect(jsonPath("$.relaxation").value(DEFAULT_RELAXATION.booleanValue()))
            .andExpect(jsonPath("$.art").value(DEFAULT_ART.booleanValue()))
            .andExpect(jsonPath("$.food").value(DEFAULT_FOOD.booleanValue()))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE.booleanValue()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCategory() throws Exception {
        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategory() throws Exception {
        // Initialize the database
        categoryService.save(category);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockCategorySearchRepository);

        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Update the category
        Category updatedCategory = categoryRepository.findById(category.getId()).get();
        // Disconnect from session so that the updates on updatedCategory are not directly saved in db
        em.detach(updatedCategory);
        updatedCategory
            .nameCategory(UPDATED_NAME_CATEGORY)
            .cheap(UPDATED_CHEAP)
            .luxury(UPDATED_LUXURY)
            .lonely(UPDATED_LONELY)
            .friends(UPDATED_FRIENDS)
            .romantic(UPDATED_ROMANTIC)
            .kids(UPDATED_KIDS)
            .sport(UPDATED_SPORT)
            .relaxation(UPDATED_RELAXATION)
            .art(UPDATED_ART)
            .food(UPDATED_FOOD)
            .nature(UPDATED_NATURE)
            .city(UPDATED_CITY);

        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategory)))
            .andExpect(status().isOk());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getNameCategory()).isEqualTo(UPDATED_NAME_CATEGORY);
        assertThat(testCategory.isCheap()).isEqualTo(UPDATED_CHEAP);
        assertThat(testCategory.isLuxury()).isEqualTo(UPDATED_LUXURY);
        assertThat(testCategory.isLonely()).isEqualTo(UPDATED_LONELY);
        assertThat(testCategory.isFriends()).isEqualTo(UPDATED_FRIENDS);
        assertThat(testCategory.isRomantic()).isEqualTo(UPDATED_ROMANTIC);
        assertThat(testCategory.isKids()).isEqualTo(UPDATED_KIDS);
        assertThat(testCategory.isSport()).isEqualTo(UPDATED_SPORT);
        assertThat(testCategory.isRelaxation()).isEqualTo(UPDATED_RELAXATION);
        assertThat(testCategory.isArt()).isEqualTo(UPDATED_ART);
        assertThat(testCategory.isFood()).isEqualTo(UPDATED_FOOD);
        assertThat(testCategory.isNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testCategory.isCity()).isEqualTo(UPDATED_CITY);

        // Validate the Category in Elasticsearch
        verify(mockCategorySearchRepository, times(1)).save(testCategory);
    }

    @Test
    @Transactional
    public void updateNonExistingCategory() throws Exception {
        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Create the Category

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(category)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Category in Elasticsearch
        verify(mockCategorySearchRepository, times(0)).save(category);
    }

    @Test
    @Transactional
    public void deleteCategory() throws Exception {
        // Initialize the database
        categoryService.save(category);

        int databaseSizeBeforeDelete = categoryRepository.findAll().size();

        // Delete the category
        restCategoryMockMvc.perform(delete("/api/categories/{id}", category.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Category in Elasticsearch
        verify(mockCategorySearchRepository, times(1)).deleteById(category.getId());
    }

    @Test
    @Transactional
    public void searchCategory() throws Exception {
        // Initialize the database
        categoryService.save(category);
        when(mockCategorySearchRepository.search(queryStringQuery("id:" + category.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(category), PageRequest.of(0, 1), 1));
        // Search the category
        restCategoryMockMvc.perform(get("/api/_search/categories?query=id:" + category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameCategory").value(hasItem(DEFAULT_NAME_CATEGORY)))
            .andExpect(jsonPath("$.[*].cheap").value(hasItem(DEFAULT_CHEAP.booleanValue())))
            .andExpect(jsonPath("$.[*].luxury").value(hasItem(DEFAULT_LUXURY.booleanValue())))
            .andExpect(jsonPath("$.[*].lonely").value(hasItem(DEFAULT_LONELY.booleanValue())))
            .andExpect(jsonPath("$.[*].friends").value(hasItem(DEFAULT_FRIENDS.booleanValue())))
            .andExpect(jsonPath("$.[*].romantic").value(hasItem(DEFAULT_ROMANTIC.booleanValue())))
            .andExpect(jsonPath("$.[*].kids").value(hasItem(DEFAULT_KIDS.booleanValue())))
            .andExpect(jsonPath("$.[*].sport").value(hasItem(DEFAULT_SPORT.booleanValue())))
            .andExpect(jsonPath("$.[*].relaxation").value(hasItem(DEFAULT_RELAXATION.booleanValue())))
            .andExpect(jsonPath("$.[*].art").value(hasItem(DEFAULT_ART.booleanValue())))
            .andExpect(jsonPath("$.[*].food").value(hasItem(DEFAULT_FOOD.booleanValue())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.booleanValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.booleanValue())));
    }
}
