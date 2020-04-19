package com.beroutes.repository.search;

import com.beroutes.domain.Valuation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Valuation} entity.
 */
public interface ValuationSearchRepository extends ElasticsearchRepository<Valuation, Long> {
}
