package com.beroutes.repository.search;

import com.beroutes.domain.TravelRoute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TravelRoute} entity.
 */
public interface TravelRouteSearchRepository extends ElasticsearchRepository<TravelRoute, Long> {
}
