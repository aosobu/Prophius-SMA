package com.spiritcoderz.prophiusassessmentprepup.comments.api;

import com.spiritcoderz.prophiusassessmentprepup.comments.model.CommentDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String message;

    private CommentDTO commentDTO;
    private List<String> errors;
}
