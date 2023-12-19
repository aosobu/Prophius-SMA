package com.spiritcoderz.prophiusassessmentprepup.posts.components;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostLikeRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.api.RegisterUserRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostLikeCommentTest extends TestConfiguration {

    @BeforeAll
    public static void before(){
        restTemplate = new RestTemplate();
        TestConfiguration.configureTestToken(restTemplate);
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/api/v1/posts/like");
    }

    @Test
    public void testWhenCreatePostLIke_withValidContent_thenSuccess(){

        PostLikeRequest postLikeRequest = PostLikeRequest.builder().postId(1).build();

        PostResponse postResponse = restTemplate.postForObject(baseUrl, postLikeRequest, PostResponse.class);
        Assertions.assertEquals(postResponse.getMessage(), "success");
    }

}
