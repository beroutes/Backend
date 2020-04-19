package com.beroutes.repository.search;

import com.beroutes.domain.Follower;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Follower} entity.
 */
public interface FollowerSearchRepository extends ElasticsearchRepository<Follower, Long> {
}
