package com.spiritcoderz.prophiusassessmentprepup.users.mapper;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.FollowerDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    List<FollowerDTO> objectToFollowerDTO(List<Object> object);
}
