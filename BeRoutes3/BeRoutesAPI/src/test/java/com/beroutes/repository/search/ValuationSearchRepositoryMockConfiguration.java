package com.beroutes.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ValuationSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ValuationSearchRepositoryMockConfiguration {

    @MockBean
    private ValuationSearchRepository mockValuationSearchRepository;

}
