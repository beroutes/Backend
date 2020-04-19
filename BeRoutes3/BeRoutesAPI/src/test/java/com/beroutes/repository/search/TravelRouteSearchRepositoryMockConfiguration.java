package com.beroutes.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link TravelRouteSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TravelRouteSearchRepositoryMockConfiguration {

    @MockBean
    private TravelRouteSearchRepository mockTravelRouteSearchRepository;

}
