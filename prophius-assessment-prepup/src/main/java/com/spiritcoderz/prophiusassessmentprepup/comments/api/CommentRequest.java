package com.spiritcoderz.prophiusassessmentprepup.comments.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "comment cannot be empty")
    private String comment;
    @NotNull(message = "user id cannot be blank")
    private Integer userId;
    @NotNull(message = "post id cannot be blank")
    private Integer postId;
}
