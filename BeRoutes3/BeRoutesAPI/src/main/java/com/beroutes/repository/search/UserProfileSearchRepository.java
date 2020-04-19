package com.beroutes.repository.search;

import com.beroutes.domain.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserProfile} entity.
 */
public interface UserProfileSearchRepository extends ElasticsearchRepository<UserProfile, Long> {
}
