package com.spiritcoderz.prophiusassessmentprepup.users.utility;

import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;

import java.util.List;

public class ErrorHandlerUtils {

    public static void updateUserResponse(UserResponse response, List<String> errors, String message){
        errors.add(message);
        response.setErrors(errors);
    }

    public static void updateUserResponse(UserResponse userResponse, String message){
        userResponse.setMessage(message);
    }
}
