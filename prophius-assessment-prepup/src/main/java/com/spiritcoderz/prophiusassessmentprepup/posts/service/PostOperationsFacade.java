package com.spiritcoderz.prophiusassessmentprepup.posts.service;

import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostLikeRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.service.components.PostComponent;
import com.spiritcoderz.prophiusassessmentprepup.posts.service.components.PostLikeComponent;
import com.spiritcoderz.prophiusassessmentprepup.posts.service.components.PostSortComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostOperationsFacade {

    private final PostLikeComponent postLikeComponent;

    private final PostComponent postComponent;

    private final PostSortComponent postSortComponent;

    public PostResponse executeLikeRequest(PostLikeRequest postLike) {
        return postLikeComponent.execute(postLike, new PostResponse());
    }

    public PostResponse getSortedPost() {
        return postSortComponent.getSortedPost(new PostResponse());
    }

    public PostResponse getPost(Integer id) {
        return postComponent.getPost(id, new PostResponse());
    }

    public PostResponse createPost(PostRequest postRequest) {
        return postComponent.savePost(postRequest, new PostResponse());
    }

    public PostResponse updatePost(Integer id) {
        return null;
    }

    public PostResponse deletePost(Integer id) {
        return null;
    }
}
