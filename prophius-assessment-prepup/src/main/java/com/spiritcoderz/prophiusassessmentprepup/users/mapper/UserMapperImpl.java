package com.spiritcoderz.prophiusassessmentprepup.users.mapper;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserDTO userToUserDTO(User user) {
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setFollowers(user.getFollowers());
        userDTO.setFollowing(user.getFollowing());
        userDTO.setProfilePicture(user.getProfilePictureFilePath());

        return userDTO;
    }
}
