package com.spiritcoderz.prophiusassessmentprepup.posts.dto;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Integer id;
    private String content;
    private Integer userId;
    private Integer likes_count;
    private UserDTO userDTO;
}
