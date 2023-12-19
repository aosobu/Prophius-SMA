package com.spiritcoderz.prophiusassessmentprepup.users.api;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileImageRequest {
    private MultipartFile profileImage;
    private Integer userId;
}
