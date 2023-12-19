package com.spiritcoderz.prophiusassessmentprepup.users.dto;

import com.spiritcoderz.prophiusassessmentprepup.posts.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Object> followers;
    private List<Object> following;
    private List<PostDTO> recentPosts;
}
