package com.beroutes.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link UserProfileSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class UserProfileSearchRepositoryMockConfiguration {

    @MockBean
    private UserProfileSearchRepository mockUserProfileSearchRepository;

}