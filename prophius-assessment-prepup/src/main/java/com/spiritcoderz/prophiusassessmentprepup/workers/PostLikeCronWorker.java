package com.spiritcoderz.prophiusassessmentprepup.workers;


import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostLikeEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.posts.service.PostDocumentCreator;
import com.spiritcoderz.prophiusassessmentprepup.posts.utils.PostDocumentUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This event sourcing algorithm is not optimal as it does not use snapshots.
 */
@Component
@RequiredArgsConstructor
public class PostLikeCronWorker {

    private final PostLikeEntityManager postLikeEntityManager;
    private final CacheManager cacheManager;
    private final PostDocumentCreator postDocumentCreator;
    Logger logger = LoggerFactory.getLogger(PostLikeCronWorker.class);

    private final String CACHE_NAME = "post-like";

    private final String CACHE_KEY = "post-keys";

    @Scheduled(cron = "*/1 * * * * *")
    public void countPostLike(){

        if( getCache(CACHE_NAME) != null ){

            List<Integer> postIdList = getCache(CACHE_NAME).get(CACHE_KEY, ArrayList.class);

            if(postIdList != null){
                postIdList.forEach( postId->{

                    Integer currentDBLikeCount = postLikeEntityManager.getCountForPostId(postId);
                    String key = PostDocumentUtils.generatePostKey(postId);

                    getCache(CACHE_NAME).evict(key);
                    getCache(CACHE_NAME).put(key, currentDBLikeCount);

                    updatePostDocument(postId, currentDBLikeCount);
                });
            }

        }

    }

    private Cache getCache(String name){
        return cacheManager.getCache(name);
    }

    public void updatePostDocument(int postKey, int likeCount){

        if( getCache(CACHE_NAME) != null ) {
            String documentKey = PostDocumentUtils.generatePostDocumentKey(postKey);
            Post cachedPost = getCache(CACHE_NAME).get(documentKey, Post.class);

            cachedPost.setLikeCount(likeCount);
            postDocumentCreator.cachePostDocument(cachedPost);
        }

    }
}
