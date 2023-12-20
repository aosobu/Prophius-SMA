package com.spiritcoderz.prophiusassessmentprepup.workers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * This cron invalidates the caches for post and user documents,
 * thus bring eventual consistency to documents in scheduled intervals
 * of five minutes
 */
@Component
@RequiredArgsConstructor
public class CacheInvalidator {

    private final CacheManager cacheManager;

    @Value("${application.cache.activate}")
    private boolean invalidateCache;

    private final String[] CACHE_NAME = {"post-like", "user", "security"};

    @Scheduled(cron = "*/5 * * * * *")
    public void countPostLike(){

        if( invalidateCache ) {
            List<String> caches = Arrays.asList(CACHE_NAME);
            caches.forEach(cache -> {
                if(getCache(cache) != null){
                    cacheManager.getCache(cache).clear();
                }
            });
            //TODO::cache rebuild
        }

    }

    private Cache getCache(String name){
        return cacheManager.getCache(name);
    }
}
