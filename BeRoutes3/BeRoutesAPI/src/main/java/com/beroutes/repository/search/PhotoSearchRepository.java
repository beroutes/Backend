package com.beroutes.repository.search;

import com.beroutes.domain.Photo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Photo} entity.
 */
public interface PhotoSearchRepository extends ElasticsearchRepository<Photo, Long> {
}
