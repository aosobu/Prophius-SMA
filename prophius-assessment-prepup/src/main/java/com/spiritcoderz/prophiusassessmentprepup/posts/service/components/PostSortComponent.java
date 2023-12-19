package com.spiritcoderz.prophiusassessmentprepup.posts.service.components;

import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostSortComponent {

    private final PostEntityManager postEntityManager;

    public PostResponse getSortedPost(PostResponse postResponse) {
        return postResponse;
    }
}
