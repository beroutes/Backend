package com.beroutes.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link FollowerSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class FollowerSearchRepositoryMockConfiguration {

    @MockBean
    private FollowerSearchRepository mockFollowerSearchRepository;

}
