package com.snap.linkshortener.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.concurrent.Executor;

public class Configuration {


    public static final String CACHE_MANAGER_NAME = "linkServiceCacheManager";

    @Value("${link-service.cache-ttl:10m}")
    private Duration cacheTtl;

    @Bean(name = CACHE_MANAGER_NAME)
    RedisCacheManager linkServiceCacheManager(RedisConnectionFactory connectionFactory) {
        var config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(cacheTtl).disableCachingNullValues();
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).transactionAware().build();
    }

    @Bean
    public Executor rateThreadPoolTaskExecutor() {
        var threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(16);
        threadPoolTaskExecutor.setMaxPoolSize(32);
        threadPoolTaskExecutor.setQueueCapacity(64);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
