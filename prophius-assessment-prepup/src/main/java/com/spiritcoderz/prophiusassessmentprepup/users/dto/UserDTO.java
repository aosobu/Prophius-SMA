package com.spiritcoderz.prophiusassessmentprepup.users.dto;

import com.spiritcoderz.prophiusassessmentprepup.posts.dto.PostDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String profilePicture;
    private List<UserDTO> followers;
    private List<UserDTO> following;
    private List<PostDTO> recentPosts;
}
