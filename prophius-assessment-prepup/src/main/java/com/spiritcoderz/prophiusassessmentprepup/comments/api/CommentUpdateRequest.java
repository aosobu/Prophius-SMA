package com.spiritcoderz.prophiusassessmentprepup.comments.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequest {
    @NotNull(message = "invalid comment id")
    private Integer commentId;

    @NotBlank(message = "comment cannot be empty")
    private String comment;
}
