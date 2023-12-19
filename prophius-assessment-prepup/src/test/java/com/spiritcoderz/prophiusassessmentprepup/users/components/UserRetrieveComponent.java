package com.spiritcoderz.prophiusassessmentprepup.users.components;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRetrieveComponent extends TestConfiguration {

    @BeforeAll
    public static void before(){
        TestConfiguration.configureTestToken(restTemplate);
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/api/v1/users");
    }

    @Test
    public void testWhenValidEmail_thenUserRetrieve_thenSuccess(){

        UserResponse userResponse = restTemplate.getForObject(baseUrl+"/email/{email}",
                                                                        UserResponse.class, "aosobu.dev@gmail.com");

        Assertions.assertNotEquals(userResponse, null);
        Assertions.assertEquals(userResponse.getMessage(), "success");
        Assertions.assertEquals(userResponse.getUserDTO().getFollowers().size(), 3);
        Assertions.assertEquals(userResponse.getUserDTO().getFollowing().size(), 2);
    }

    @Test
    public void testWhenValidId_thenUserRetrieve_thenSuccess(){

        UserResponse userResponse = restTemplate.getForObject(baseUrl+"/id/{id}",
                UserResponse.class, 3);

        Assertions.assertNotEquals(userResponse, null);
        Assertions.assertEquals(userResponse.getMessage(), "success");
        Assertions.assertEquals(userResponse.getUserDTO().getFollowers().size(), 2);
        Assertions.assertEquals(userResponse.getUserDTO().getFollowing().size(), 1);
    }

    @Test
    public void testWhenInValidId_thenUserRetrieve_thenFaiure(){

        UserResponse userResponse = restTemplate.getForObject(baseUrl+"/id/{id}",
                UserResponse.class, 6);

        Assertions.assertNotEquals(userResponse, null);
    }

    @Test
    public void testWhenInValidEmail_thenUserRetrieve_thenFailure(){

        UserResponse userResponse = restTemplate.getForObject(baseUrl+"/email/{email}",
                UserResponse.class, "dot@brown@com");

        Assertions.assertNotEquals(userResponse, null);
    }
}
