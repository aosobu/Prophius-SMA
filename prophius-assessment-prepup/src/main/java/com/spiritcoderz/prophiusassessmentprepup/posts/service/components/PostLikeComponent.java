package com.spiritcoderz.prophiusassessmentprepup.posts.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostLikeRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.PostLike;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostLikeEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostLikeComponent {

    private final PostLikeEntityManager postLikeEntityManager;

    public PostResponse execute(PostLikeRequest postLikeRequest, PostResponse postResponse) {

        PostLike postLike = new PostLike();
        postLike.setPostId(postLikeRequest.getPostId());

        if( postLike.getPostId() > 0 ){
            PostLike savedPostLike = postLikeEntityManager.savePostLike(postLike);
            return updatePostResponseWithMessage(postResponse, savedPostLike);
        }

        return postResponse;
    }

    public PostResponse updatePostResponseWithMessage(PostResponse postResponse, PostLike savedPostLike){

        if( savedPostLike != null ){
            postResponse.setMessage(AppConstants.POSTLIKE_REQUEST_SUCCESS);
        }else{
            postResponse.setMessage(AppConstants.POSTLIKE_CREATION_FAILURE);
        }

        return postResponse;
    }
}
