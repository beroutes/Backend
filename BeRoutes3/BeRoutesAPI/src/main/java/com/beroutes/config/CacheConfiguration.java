package com.beroutes.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.beroutes.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.beroutes.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.beroutes.domain.User.class.getName());
            createCache(cm, com.beroutes.domain.Authority.class.getName());
            createCache(cm, com.beroutes.domain.User.class.getName() + ".authorities");
            createCache(cm, com.beroutes.domain.Region.class.getName());
            createCache(cm, com.beroutes.domain.City.class.getName());
            createCache(cm, com.beroutes.domain.Country.class.getName());
            createCache(cm, com.beroutes.domain.Location.class.getName());
            createCache(cm, com.beroutes.domain.TravelRoute.class.getName());
            createCache(cm, com.beroutes.domain.TravelRoute.class.getName() + ".locations");
            createCache(cm, com.beroutes.domain.TravelRoute.class.getName() + ".photos");
            createCache(cm, com.beroutes.domain.TravelRoute.class.getName() + ".valuations");
            createCache(cm, com.beroutes.domain.UserProfile.class.getName());
            createCache(cm, com.beroutes.domain.UserProfile.class.getName() + ".travelRoutes");
            createCache(cm, com.beroutes.domain.UserProfile.class.getName() + ".valuations");
            createCache(cm, com.beroutes.domain.UserProfile.class.getName() + ".followers");
            createCache(cm, com.beroutes.domain.Follower.class.getName());
            createCache(cm, com.beroutes.domain.Follower.class.getName() + ".userProfiles");
            createCache(cm, com.beroutes.domain.Duration.class.getName());
            createCache(cm, com.beroutes.domain.Category.class.getName());
            createCache(cm, com.beroutes.domain.Photo.class.getName());
            createCache(cm, com.beroutes.domain.Valuation.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
