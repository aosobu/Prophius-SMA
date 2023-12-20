package com.spiritcoderz.prophiusassessmentprepup.users.service;

import com.spiritcoderz.prophiusassessmentprepup.users.api.*;
import com.spiritcoderz.prophiusassessmentprepup.users.service.components.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserOperationsFacade {
    private final UserSaveComponent userSaveComponent;
    private final UserRetrieveComponent userRetrieveComponent;
    private final UserUpdateComponent userUpdateComponent;
    private final FollowComponent followComponent;
    private final UserDeleteComponent userDeleteComponent;

    public UserResponse saveUser(RegisterUserRequest userRequest){
        return userSaveComponent.saveUser(userRequest, new UserResponse());
    }

    public UserResponse getUserByEmail(String email) {
        return userRetrieveComponent.retrieveUserByEmail(email, new UserResponse(), new ArrayList<>());
    }

    public UserResponse updateUserDetails(UpdatePasswordRequest updateRequest) {
        return userUpdateComponent.updatePassword(updateRequest, new UserResponse());
    }

    public UserResponse updateUserDetails(UpdateImageRequest updateRequest) {
        return userUpdateComponent.updateProfileImage(updateRequest, new UserResponse());
    }

    public UserResponse executeFollowRequest(FollowRequest followRequest, UserResponse userResponse) {
        return followComponent.execute(followRequest, userResponse);
    }

    public UserResponse deleteUser(String email) {
        return userDeleteComponent.deleteUser(email, new UserResponse());
    }
}
