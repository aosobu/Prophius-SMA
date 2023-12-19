package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.mapper.UserMapperImpl;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.utility.ErrorHandlerUtils;
import com.spiritcoderz.prophiusassessmentprepup.users.utility.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserRetrieveComponent {

    private final UserEntityManager userEntityManager;
    private final FollowerEntityManager followerEntityManager;
    private final UserMapperImpl userMapper;


    public UserResponse retrieveUserById(Integer id, UserResponse userResponse, List<String> errors) {

        Optional<User> user = userEntityManager.getUserById(id);

        if(user.isPresent()){

            UserDTO userDTO = createUserDTO(user.get());
            userResponse.setUserDTO( userDTO );

            ErrorHandlerUtils.updateUserResponse( userResponse, AppConstants.USER_REGISTER_SUCCESS_MESSAGE);
        }


        if(user.isEmpty()){
            errors.add(AppConstants.USER_REGISTER_FAILURE_MESSAGE);
            userResponse.setErrors(errors);
        }

        return userResponse;
    }


    public UserResponse retrieveUserByEmail(String email, UserResponse userResponse, List<String> errors) {
        Optional<User> user = null;

        if( validateEmail(email) )
            user = userEntityManager.getUserByEmail(email);

        if(user.isPresent()){

            UserDTO userDTO = createUserDTO(user.get());
            userResponse.setUserDTO(userDTO);

            ErrorHandlerUtils.updateUserResponse( userResponse, AppConstants.USER_REGISTER_SUCCESS_MESSAGE);
        }


        if(user.isEmpty()){

            errors.add(AppConstants.USER_RETRIEVE_FAILURE_MESSAGE);
            userResponse.setErrors(errors);
        }

        return userResponse;
    }

    private Boolean validateEmail(String email) {
        return Pattern.matches(AppConstants.EMAIL_REGEX, email);
    }


    private UserDTO createUserDTO(User user) {

        UserDTO userDTO;
        userDTO = userMapper.userToUserDTO(user);

        List<Object> followerList = followerEntityManager.findAllFollowers(userDTO.getId());
        List<Object> followingList = followerEntityManager.findAllFollowing(userDTO.getId());

        userDTO.setFollowers(followerList);
        userDTO.setFollowing(followingList);

        return userDTO;
    }
}
