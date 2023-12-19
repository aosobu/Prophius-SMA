package com.spiritcoderz.prophiusassessmentprepup.users.api;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowRequest {
    private Integer userId;
    private Integer followerId;
}
