package com.spiritcoderz.prophiusassessmentprepup.users.mapper;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.FollowerDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        userDTO.setProfilePicture(user.getProfilePictureFilePath());

        return userDTO;
    }

    @Override
    public List<FollowerDTO> objectToFollowerDTO(List<Object> object) {
        if(object.isEmpty()){
            return null;
        }

        List<FollowerDTO> userList = new ArrayList<>();
        return userList;
    }
}
