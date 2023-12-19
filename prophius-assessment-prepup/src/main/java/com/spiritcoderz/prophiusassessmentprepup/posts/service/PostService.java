package com.spiritcoderz.prophiusassessmentprepup.posts.service;

import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostLikeRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostOperationsFacade postOPerations;

    public PostResponse executeLikePostOPeration(PostLikeRequest postLike) {
        return postOPerations.executeLikeRequest(postLike);
    }

    public PostResponse getNewestPosts() {
        return postOPerations.getSortedPost();
    }

    public PostResponse getOldestPosts() {
        return postOPerations.getSortedPost();
    }

    public PostResponse getNextPage(int offset, int pageSize) {
        return null;
    }

    public PostResponse getPostById(Integer id) {
        return postOPerations.getPost(id);
    }

    public PostResponse updatePost(Integer id) {
        return postOPerations.updatePost(id);
    }

    public PostResponse deletePost(Integer id) {
        return postOPerations.deletePost(id);
    }

    public PostResponse savePost(PostRequest postRequest) {
        return postOPerations.createPost(postRequest);
    }
}
