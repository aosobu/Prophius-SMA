package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.users.api.RegisterUserRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.exception.CustomProphiusException;

public interface ValidatorManager {

    UserResponse validate(RegisterUserRequest userRequest, UserResponse userResponse) throws CustomProphiusException;
}
