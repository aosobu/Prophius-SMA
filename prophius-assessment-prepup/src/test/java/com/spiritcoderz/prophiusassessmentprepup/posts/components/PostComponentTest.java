package com.spiritcoderz.prophiusassessmentprepup.posts.components;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostLikeRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostComponentTest extends TestConfiguration {

    @BeforeAll
    public static void before(){
        restTemplate = new RestTemplate();
        TestConfiguration.configureTestToken(restTemplate);
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/api/v1/posts");
    }

    @Test
    public void testWhenCreatePost_withValidContent_thenSuccess(){

        PostRequest postRequest = PostRequest.builder().content("hello now!!").userId(1).build();

        PostResponse postResponse = restTemplate.postForObject(baseUrl, postRequest, PostResponse.class);
        Assertions.assertEquals(postResponse.getMessage(), "success");
    }

    @Test
    public void testWhenCreatePost_withInValidContent_thenFailure(){

        PostRequest postRequest = PostRequest.builder().content("").userId(1).build();

        Exception exception = Assertions.assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForObject(baseUrl, postRequest, PostResponse.class));

        Assertions.assertEquals(exception.getMessage(), "403 : [no body]");
    }
}
