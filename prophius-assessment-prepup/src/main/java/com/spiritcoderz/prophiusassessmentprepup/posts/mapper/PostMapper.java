package com.spiritcoderz.prophiusassessmentprepup.posts.mapper;

import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.dto.PostDTO;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;

import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    Post postRequestToPost(PostRequest postRequest);

    PostDTO mapPostToPostDTO(Post post);
}
