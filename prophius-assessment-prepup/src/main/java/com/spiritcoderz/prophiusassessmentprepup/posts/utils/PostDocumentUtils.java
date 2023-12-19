package com.spiritcoderz.prophiusassessmentprepup.posts.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDocumentUtils {

    private final CacheManager cacheManager;
    public static String generatePostKey(Integer key){
        return "#post-"+key;
    }

    public static String generatePostDocumentKey(Integer key){
        return "#post-document-"+key;
    }
}
