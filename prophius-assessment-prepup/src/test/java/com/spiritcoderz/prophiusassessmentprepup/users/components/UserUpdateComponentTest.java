package com.spiritcoderz.prophiusassessmentprepup.users.components;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UpdatePasswordRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserUpdateComponentTest extends TestConfiguration {

    @BeforeAll
    public static void before(){
        restTemplate = new RestTemplate();
        TestConfiguration.configureTestToken(restTemplate);
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/api/v1/users/password");
    }

    @Test
    public void testWhenValidEmail_thenUserUpdate_thenSuccess(){

        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder().userId(1).password("lovelyPetz").build();

        restTemplate.put(baseUrl, updatePasswordRequest);
    }

    @Test
    public void testWhenInValidEmail_thenUserUpdate_thenFailure(){

        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder().userId(1).password("lovely").build();

        Exception exception = Assertions.assertThrows(HttpClientErrorException.class,
                () -> restTemplate.put(baseUrl, updatePasswordRequest));
    }
}
