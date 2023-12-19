package com.spiritcoderz.prophiusassessmentprepup.posts.mapper;

import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.dto.PostDTO;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {
    @Override
    public Post postRequestToPost(PostRequest postRequest) {
        if( postRequest == null ){
            return null;
        }

        return Post.builder().content(postRequest.getContent()).userId(postRequest.getUserId()).build();
    }

    @Override
    public PostDTO mapPostToPostDTO(Post post) {
        if( post == null ){
            return null;
        }

        return PostDTO
                .builder().
                id(post.getId())
                .content(post.getContent())
                .userId(post.getUserId())
                .likes_count(post.getLikeCount())
                .build();
    }
}
