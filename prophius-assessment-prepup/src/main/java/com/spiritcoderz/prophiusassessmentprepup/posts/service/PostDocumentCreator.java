package com.spiritcoderz.prophiusassessmentprepup.posts.service;

import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.posts.utils.PostDocumentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostDocumentCreator {

    private final CacheManager cacheManager;


    public Optional<Post> updatePostWithLikeCount(Post retrievedPost){

        String postKey = PostDocumentUtils.generatePostKey(retrievedPost.getId());
        Integer postLikeCount = cacheManager.getCache("post-like").get(postKey, Integer.class);

        retrievedPost.setLikeCount( postLikeCount );
        cachePostDocument( retrievedPost );

        return Optional.of(retrievedPost);
    }

    public void cachePostDocument(Post post){

        String postDocumentKey = getDocumentKey(post.getId());
        cacheManager.getCache("post-like").put(postDocumentKey, post);

    }

    public boolean canRetrievePostFromCache(Integer id) {

        String postDocumentKey = getDocumentKey(id);
        return cacheManager.getCache("post-like").get(postDocumentKey) != null;

    }

    public Optional<Post> getPost(Integer id) {

        String postDocumentKey = getDocumentKey(id);
        Post cachedPost = cacheManager.getCache("post-like").get(postDocumentKey, Post.class);

        return Optional.of(cachedPost);
    }

    private String getDocumentKey(int id){
        return PostDocumentUtils.generatePostDocumentKey(id);
    }
}
