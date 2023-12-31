package com.spiritcoderz.prophiusassessmentprepup.users.service;

import com.spiritcoderz.prophiusassessmentprepup.users.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserOperationsFacade userOperations;

    public UserResponse registerUser(RegisterUserRequest userRequest){
        return userOperations.saveUser(userRequest);
    }

    public UserResponse getUserByEmailCredential(String email) {
        return userOperations.getUserByEmail(email);
    }

    public UserResponse getUserByIdCredential(int id) {
        return userOperations.getUserById(id);
    }

    public UserResponse updateUserPassword(UpdatePasswordRequest updateRequest) {
        return userOperations.updateUserDetails(updateRequest);
    }

    public UserResponse updateUserImage(UpdateImageRequest updateRequest) {
        return userOperations.updateUserDetails(updateRequest);
    }

    public UserResponse executeFollowRequest(FollowRequest followRequest) {
        return userOperations.executeFollowRequest(followRequest);
    }

    public UserResponse deleteUser(String email) {
        return userOperations.deleteUser(email);
    }
}
