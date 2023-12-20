package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.commons.wrappers.BeanWrapper;
import com.spiritcoderz.prophiusassessmentprepup.users.api.RegisterUserRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.enums.Role;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSaveComponent {

    private final UserEntityManager userEntityManager;
    private final BeanWrapper beanWrapper;
    private final List<ValidatorManager> validatorProviders;
    private final UserCacheManagementComponent userCacheManagementComponent;


    public UserResponse saveUser(RegisterUserRequest userRequest, UserResponse userResponse){
        UserResponse [] validationResponseContainer = new UserResponse[1];
        validationResponseContainer[0] = userResponse;

        validatorProviders
                .forEach( validatorProvider ->
                            validationResponseContainer[0] = validatorProvider.validate(userRequest, validationResponseContainer[0]));


        UserResponse validationResponse = validationResponseContainer[0];


        if(!validationResponse.isEmptyErrorList()){

            validationResponse.setMessage(AppConstants.USER_REGISTER_FAILURE_MESSAGE);
            return validationResponse;
        }


        if( validationResponse.isEmptyErrorList() ){

            String username = extractUserName( userRequest.getEmail() );
            User newUser = buildUser( username, userRequest );
            User savedUser = userEntityManager.saveUser( newUser );
            userCacheManagementComponent.addUserToCache(savedUser);
            updateUserResponse(savedUser, userResponse);

        }

        return userResponse;
    }

    private void updateUserResponse(User savedUser, UserResponse userResponse) {

        if(savedUser != null){
            userResponse.setMessage(AppConstants.USER_REGISTER_SUCCESS_MESSAGE);
            userResponse.setUserDTO(buildUserDTO(savedUser));
        }else{
            userResponse.setMessage(AppConstants.USER_REGISTER_FAILURE_MESSAGE);
        }
    }

    private String extractUserName(String email) {

        int indexOfAtSymbol = email.indexOf('@');
        return email.substring(0, indexOfAtSymbol);
    }

    private User buildUser(String username, RegisterUserRequest userRequest) {

           return User
                    .builder()
                    .username(username)
                    .email(userRequest.getEmail())
                    .profilePictureFilePath("")
                    .isEnabled(true)
                    .password(beanWrapper.getPasswordEncoder().encode(userRequest.getPassword()))
                    .role(Role.USER)
                    .build();
    }

    private UserDTO buildUserDTO(User savedUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(savedUser.getUsername());
        userDTO.setEmail(savedUser.getEmail());
      return userDTO;
    }
}
