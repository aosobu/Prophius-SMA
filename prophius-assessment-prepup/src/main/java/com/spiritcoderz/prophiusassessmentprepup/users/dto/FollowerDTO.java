package com.spiritcoderz.prophiusassessmentprepup.users.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerDTO {
    private Integer id;
    private String username;
    private String email;
    private String profilePicture;
}
