package com.spiritcoderz.prophiusassessmentprepup.posts.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spiritcoderz.prophiusassessmentprepup.posts.dto.PostDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PostResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("post")
    private PostDTO postDTO;
}
