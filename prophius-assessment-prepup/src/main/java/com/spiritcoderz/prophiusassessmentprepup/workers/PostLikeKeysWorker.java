package com.spiritcoderz.prophiusassessmentprepup.workers;

import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostLikeEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.posts.utils.PostDocumentUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostLikeKeysWorker {
    private final PostLikeEntityManager postLikeEntityManager;
    private final CacheManager cacheManager;
    private final String CACHE_NAME = "post-like";
    private final String CACHE_KEY = "post-keys";
    Logger logger = LoggerFactory.getLogger(PostLikeKeysWorker.class);

    //@Scheduled(cron = "*/2 * * * * *")
    public void countPostLike(){

        if(getCache(CACHE_NAME) != null){

            List<Integer> keys = postLikeEntityManager.selectDistinctPostIds();
            getCache(CACHE_NAME).put(CACHE_KEY, keys);
            addPostKeyToCache(keys);
        }
    }

    private boolean isKeyExistsInCache(Cache.ValueWrapper value){
        return value == null;
    }


    private Cache getCache(String name){
        return cacheManager.getCache("post-like");
    }

    public void addPostKeyToCache(List<Integer> keys){
        keys.forEach(key -> {

            String documentKey = PostDocumentUtils.generatePostKey(key);
            Cache.ValueWrapper value = getCache(CACHE_NAME).get(documentKey);

            if(isKeyExistsInCache(value)){
                getCache(CACHE_NAME).put(documentKey, 0);
            }
        });
    }
}
