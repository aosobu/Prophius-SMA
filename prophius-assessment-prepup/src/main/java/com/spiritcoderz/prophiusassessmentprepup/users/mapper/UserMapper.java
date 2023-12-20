package com.spiritcoderz.prophiusassessmentprepup.users.mapper;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
}
