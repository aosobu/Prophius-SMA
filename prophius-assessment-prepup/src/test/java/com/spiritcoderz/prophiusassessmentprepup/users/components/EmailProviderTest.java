package com.spiritcoderz.prophiusassessmentprepup.users.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.RegisterUserRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserRepository;
import com.spiritcoderz.prophiusassessmentprepup.users.service.components.EmailValidationProvider;
import com.spiritcoderz.prophiusassessmentprepup.users.service.components.UserCacheManagementComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmailProviderTest {

    private static EmailValidationProvider emailValidationProvider;
    @Mock
    private static UserEntityManager userEntityManager;
    private static RegisterUserRequest userRequest;
    private static User existingUser;
    private static Optional<User> existingUserWithRequestEmail;

    @BeforeAll
    public static void before(){
        userRequest = RegisterUserRequest.builder().email("aosobu.dev@gmail.com").build();
        existingUser = User.builder().email("aosobu.dev@gmail.com").build();
        existingUserWithRequestEmail = Optional.of(existingUser);

        userEntityManager = new UserEntityManager(Mockito.mock(UserRepository.class));
        emailValidationProvider = new EmailValidationProvider(userEntityManager);
    }

    @Test
    public void whenUserRequest_thenContainsUniqueEmail_thenSuccess(){
        UserResponse existingResponse = new UserResponse();
        List<String> errors = new ArrayList<>();
        existingResponse.setErrors(errors);

        existingUserWithRequestEmail = Optional.empty();

        Mockito
                .when(userEntityManager.getUserByEmail(userRequest.getEmail()))
                .thenReturn(existingUserWithRequestEmail);

        UserResponse userResponse = emailValidationProvider.validate(userRequest, existingResponse);
        Assertions.assertEquals(userResponse.getErrors().isEmpty(), true);
    }

    @Test
    public void whenUserRequest_thenContainsNonUniqueEmail_thenFailure(){
        UserResponse existingResponse = new UserResponse();
        List<String> errors = new ArrayList<>();

        existingResponse.setErrors(errors);

        Mockito
                .when(userEntityManager.getUserByEmail(userRequest.getEmail()))
                .thenReturn(existingUserWithRequestEmail);

        UserResponse userResponse = emailValidationProvider.validate(userRequest, existingResponse);
        Assertions.assertFalse(userResponse.getErrors().isEmpty());
    }

    @Test
    public void whenUserRequest_thenExistingError_thenErrorListIsUpdated_thenSuccess(){
        UserResponse existingResponse = new UserResponse();
        List<String> errors = new ArrayList<>();
        errors.add(AppConstants.USER_REGISTER_FAILURE_MESSAGE);
        existingResponse.setErrors(errors);

        Mockito
                .when(userEntityManager.getUserByEmail(userRequest.getEmail()))
                .thenReturn(existingUserWithRequestEmail);

        UserResponse userResponse = emailValidationProvider.validate(userRequest, existingResponse);
        Assertions.assertEquals(userResponse.getErrors().size(), 2);
    }
}
