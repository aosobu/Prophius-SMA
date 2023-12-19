package com.spiritcoderz.prophiusassessmentprepup.posts.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.dto.PostDTO;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.posts.mapper.PostMapperImpl;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostComponent {

    private final PostEntityManager postEntityManager;

    private final PostMapperImpl postMapperImpl;

    public PostResponse savePost(PostRequest postRequest, PostResponse postResponse){

        Post post = postMapperImpl.postRequestToPost(postRequest);
        Post savedPost = postEntityManager.savePost(post);


        if(savedPost != null){

            PostDTO postDTO = postMapperImpl.mapPostToPostDTO(savedPost);

            postResponse.setMessage(AppConstants.POST_CREATION_SUCCESS);
            postResponse.setPostDTO(postDTO);
        }


        if(savedPost == null){
            postResponse.setMessage(AppConstants.POST_CREATION_FAILURE);
        }

        return postResponse;
    }

    public PostResponse getPost(Integer id, PostResponse postResponse) {

        if(id > 0){
            Optional<Post> post = postEntityManager.getPost(id);
            updatePostResponse(post, postResponse);
        }

        return postResponse;
    }

    private void updatePostResponse(Optional<Post> post, PostResponse postResponse){

        if(post.isPresent()){

            PostDTO postDTO = postMapperImpl.mapPostToPostDTO(post.get());
            postResponse.setPostDTO(postDTO);
            postResponse.setMessage(AppConstants.POST_RETRIEVAL_SUCCESS);
        }else{
            postResponse.setMessage(AppConstants.POST_RETRIEVAL_FAILURE);
        }
    }
}
