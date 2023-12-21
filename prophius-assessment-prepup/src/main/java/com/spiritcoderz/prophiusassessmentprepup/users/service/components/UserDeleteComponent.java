package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDeleteComponent {

    private final UserEntityManager userEntityManager;
    public UserResponse deleteUser(String email, UserResponse userResponse) {
        Optional<User> user = userEntityManager.getUserByEmail(email);

        if(user.isPresent()) {
            User deleteUser = user.get();
            deleteUser.setEnabled(false);

            deleteUser = userEntityManager.saveUser(deleteUser);
            updateUserResponseWithDeleteSuccess(deleteUser, userResponse);
        }

        if(user.isEmpty()){
            userResponse.setMessage(AppConstants.DELETE_REQUEST_FAILURE);
        }

        return userResponse;
    }

    public void updateUserResponseWithDeleteSuccess(User deletedUser, UserResponse userResponse){
        if(deletedUser != null){
            userResponse.setMessage(AppConstants.DELETE_REQUEST_SUCCESS);
        }
    }

}
