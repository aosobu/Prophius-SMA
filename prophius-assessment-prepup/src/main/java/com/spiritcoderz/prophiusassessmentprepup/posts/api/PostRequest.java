package com.spiritcoderz.prophiusassessmentprepup.posts.api;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    @NotBlank(message = "post content cannot be blank")
    @NotEmpty
    private String content;

    @NotNull
    private Integer userId;
}
