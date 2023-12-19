package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.RegisterUserRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailValidationProvider implements ValidatorManager {

    private final UserEntityManager userEntityManager;

    @Override
    public UserResponse validate(RegisterUserRequest userRequest, UserResponse userResponse) {

        String email = userRequest.getEmail();
        Optional<User> existingUserWithRequestEmail = userEntityManager.getUserByEmail(email);

        if(existingUserWithRequestEmail.isEmpty()){
            return userResponse;
        }

        if(existingUserWithRequestEmail.isPresent()){

            List<String> existingErrorList = userResponse.getErrors();
            existingErrorList.add(AppConstants.EMAIL_ALREADY_EXISTS);
            userResponse.setErrors(existingErrorList);
        }

        return userResponse;
    }
}
