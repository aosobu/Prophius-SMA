package com.spiritcoderz.prophiusassessmentprepup.comment;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentSaveComponentTest extends TestConfiguration {

    @BeforeAll
    public static void before(){
        restTemplate = new RestTemplate();
        TestConfiguration.configureTestToken(restTemplate);
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/api/v1/comments");
    }

    @Test
    public void testWhenCreateComment_withValidInContent_thenFailure(){

        CommentRequest commentRequest = CommentRequest.builder().comment("a").postId(1).userId(1).build();

        CommentResponse commentResponse = restTemplate.postForObject(baseUrl, commentRequest, CommentResponse.class);
        Assertions.assertEquals(commentResponse.getMessage(), "success");
    }
}
