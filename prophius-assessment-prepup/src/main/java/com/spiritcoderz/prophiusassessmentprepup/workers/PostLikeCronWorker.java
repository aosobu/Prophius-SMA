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
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostLikeCronWorker {

    private final PostLikeEntityManager postLikeEntityManager;
    private final CacheManager cacheManager;
    Logger logger = LoggerFactory.getLogger(PostLikeCronWorker.class);

    private final String CACHE_NAME = "post-like";

    private final String CACHE_KEY = "post-keys";

    @Scheduled(cron = "*/1 * * * * *")
    public void countPostLike(){

        logger.info("Starting PostLike Cron " + LocalDateTime.now());

        if( getCache(CACHE_NAME) != null ){

            List<Integer> postIdList = getCache(CACHE_NAME).get(CACHE_KEY, ArrayList.class);
            postIdList.forEach( postId->{

                Integer currentDBLikeCount = postLikeEntityManager.getCountForPostId(postId);
                String key = PostDocumentUtils.generatePostKey(postId);

                Integer postLikeCountSnapShot = getCache(CACHE_NAME).get(key, Integer.class);
                postLikeCountSnapShot += currentDBLikeCount;

                getCache(CACHE_NAME).put(key, postLikeCountSnapShot);
            });
        }

        logger.info("Exiting PostLike Cron " + LocalDateTime.now());
    }

    private Cache getCache(String name){
        return cacheManager.getCache(name);
    }
}
