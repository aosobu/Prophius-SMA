package com.spiritcoderz.prophiusassessmentprepup.users.components;

import com.spiritcoderz.prophiusassessmentprepup.TestConfiguration;
import com.spiritcoderz.prophiusassessmentprepup.users.UserController;
import com.spiritcoderz.prophiusassessmentprepup.users.api.RegisterUserRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSaveComponentTest extends TestConfiguration {

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
    public void testWhenCreateUser_thenInvalidEmail_thenExceptionThrown(){

        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder().email("anulover@").password("12345678").build();

        Exception exception = Assertions.assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForObject(baseUrl, registerUserRequest, UserResponse.class));

        Assertions.assertEquals(exception.getMessage(), "403 : [no body]");
    }

    @Test
    public void testWhenCreateUser_thenInvalidPassword_thenExceptionThrown(){

        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder().email("anulover@gmail.com").password("1234567").build();

        Exception exception = Assertions.assertThrows(HttpClientErrorException.class,
                () -> restTemplate.postForObject(baseUrl, registerUserRequest, UserResponse.class));

        Assertions.assertEquals(exception.getMessage(), "403 : [no body]");
    }

    @Test
    public void testWhenUserRegisters_thenCredentialsValidated_thenSuccess(){

        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder().email("anulover@gmail.com").password("123456789").build();
        UserResponse userResponse = restTemplate.postForObject(baseUrl, registerUserRequest, UserResponse.class);

        Assertions.assertEquals(userResponse.getUserDTO().getEmail(), registerUserRequest.getEmail());
    }

    @Test
    public void testWhenUserRegisters_thenEmailExists_thenFailure(){

        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder().email("aosobu.dev@gmail.com").password("12346578").build();
        UserResponse userResponse = restTemplate.postForObject(baseUrl, registerUserRequest, UserResponse.class);

        Assertions.assertTrue(userResponse.getErrors().contains("email already used"));
        Assertions.assertEquals(userResponse.getErrors().size(), 1);
        Assertions.assertEquals(userResponse.getUserDTO(), null);
    }

}
