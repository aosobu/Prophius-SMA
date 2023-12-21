package com.spiritcoderz.prophiusassessmentprepup.workers;

import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
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

    @Scheduled(cron = "*/2 * * * * *")
    public void countPostLike(){

        if(getCache(CACHE_NAME) != null){
            List<Integer> keys = postLikeEntityManager.selectDistinctPostIds();
            getCache(CACHE_NAME).put(CACHE_KEY, keys);
            addPostKeyAndDocumentKeyToCache(keys);
        }
    }

    private boolean isKeyExistsInCache(Cache.ValueWrapper value){
        return value == null;
    }


    private Cache getCache(String name){
        return cacheManager.getCache("post-like");
    }

    public void addPostKeyAndDocumentKeyToCache(List<Integer> keys){
        keys.forEach(key -> {

            String postKey = PostDocumentUtils.generatePostKey(key);
            Cache.ValueWrapper post = getCache(CACHE_NAME).get(postKey);

            if(isKeyExistsInCache(post)){
                getCache(CACHE_NAME).put(postKey, 0);
            }

        });
    }
}
