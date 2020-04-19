package com.beroutes.repository.search;

import com.beroutes.domain.Duration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Duration} entity.
 */
public interface DurationSearchRepository extends ElasticsearchRepository<Duration, Long> {
}
