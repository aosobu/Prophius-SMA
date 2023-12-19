package com.spiritcoderz.prophiusassessmentprepup.users.components;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UpdatePasswordRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDelelteComponentTest extends TestConfiguration {

    @BeforeAll
    public static void before(){
        restTemplate = new RestTemplate();
        TestConfiguration.configureTestToken(restTemplate);
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/api/v1/users/{id}");
    }

    @Test
    public void testWhenInValidId_thenUserDelete_thenSuccess(){
        restTemplate.delete(baseUrl, 2);
    }

    @Test
    public void testWhenInValidId_thenUserDelete_thenFailure(){
        restTemplate.delete(baseUrl, 23);
    }
}
