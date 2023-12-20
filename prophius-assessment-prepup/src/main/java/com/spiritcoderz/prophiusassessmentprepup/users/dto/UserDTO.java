package com.spiritcoderz.prophiusassessmentprepup.users.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String profilePicture;
    private List<UserRecord> followers;
    private List<UserRecord> following;
    private List<UserRecord> recentPosts;
}
