package com.spiritcoderz.prophiusassessmentprepup.users.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateImageRequest {

    @NotBlank
    private Integer userId;

    @NotNull(message = "image cannot be empty")
    private MultipartFile image;
}