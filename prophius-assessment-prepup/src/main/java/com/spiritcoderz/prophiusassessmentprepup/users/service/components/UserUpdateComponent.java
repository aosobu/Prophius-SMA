package com.spiritcoderz.prophiusassessmentprepup.users.service.components;


import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.commons.wrappers.BeanWrapper;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UpdateImageRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UpdatePasswordRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.exception.CustomProphiusException;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManagerWrapper;
import com.spiritcoderz.prophiusassessmentprepup.users.utility.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateComponent {

    private final UserEntityManager userEntityManager;

    private final UserEntityManagerWrapper userEntityManagerWrapper;
    private final BeanWrapper beanWrapper;
    private CacheManager cacheManager;

    public UserResponse updatePassword(UpdatePasswordRequest updateRequest, UserResponse userResponse) {

        Optional<User> user = userEntityManagerWrapper.getUserByEmail(updateRequest.getEmail());

        if(user.isPresent()){

            User updatedUser = user.get();
            String updatedPassword = beanWrapper.getPasswordEncoder().encode(updateRequest.getPassword());
            updatedUser.setPassword(updatedPassword);

            User savedUser = userEntityManager.saveUser(updatedUser);
            return checkIfUpdateSuccessful(savedUser, updatedUser, userResponse);

        }


        userResponse.setMessage(AppConstants.USER_UPDATE_FAILURE_MESSAGE);
        return userResponse;
    }

    private UserResponse checkIfUpdateSuccessful(User savedUser, User updatedUser, UserResponse userResponse){
        if(savedUser.getId().equals(updatedUser.getId())){
            userResponse.setMessage(AppConstants.USER_UPDATE_SUCCESS_MESSAGE);
        }else{
            userResponse.setMessage(AppConstants.USER_UPDATE_FAILURE_MESSAGE);
        }
        return userResponse;
    }

    public UserResponse  updateProfileImage(UpdateImageRequest updateRequest, UserResponse userResponse) {

        Optional<User> userCopy = Optional.empty();
        MultipartFile profileImage = updateRequest.getImage();

        if(profileImage.getOriginalFilename() == null) {
            userResponse = new UserResponse();
            userResponse.setMessage(AppConstants.IMAGE_NAME_INVALID);
            return userResponse;
        }

        if(profileImage.getOriginalFilename() != null){
            Optional<User> savedUser = userEntityManagerWrapper.getUserByEmail(updateRequest.getEmail());
            userCopy = updateUserProfileImageDetails( savedUser, profileImage );
        }

        if(userCopy.isPresent()){
            User savedUser = userCopy.get();
            userEntityManager.saveUser( savedUser );
            userResponse.setMessage( AppConstants.USER_IMAGE_UPLOAD_SUCCESSFUL );

            UserDTO userDTO = buildUserDTO(savedUser);
            userResponse.setUserDTO(userDTO);

            return userResponse;
        }

        userResponse.setMessage(AppConstants.USER_IMAGE_UPLOAD_FAILURE);
        return userResponse;
    }

    private Optional<User> updateUserProfileImageDetails( Optional<User> savedUser, MultipartFile profileImage ){
        if(savedUser.isPresent()){

            String uniqueFileName = AppConstants.FILE_STORAGE_PATH;
            uniqueFileName = uniqueFileName.concat(ImageUtils.generateUniqueFileName(profileImage.getOriginalFilename()));
            User user = savedUser.get();
            user.setImageData( compressImage( profileImage) );
            user.setProfilePictureFilePath( uniqueFileName );
            return Optional.of(user);
        }

        return Optional.empty();
    }

    private byte[] compressImage(MultipartFile imageData){
        try {
            return ImageUtils.compressImage(imageData.getBytes());
        } catch (IOException e) {
            throw new CustomProphiusException(e.getMessage());
        }
    }

    private UserDTO buildUserDTO(User savedUser){
        UserDTO userDTO = new UserDTO();
        userDTO.setProfilePicture(savedUser.getProfilePictureFilePath());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setUsername(savedUser.getUsername());
        return userDTO;
    }
}
