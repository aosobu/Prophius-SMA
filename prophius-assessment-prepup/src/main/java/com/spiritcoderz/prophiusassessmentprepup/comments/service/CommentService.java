package com.spiritcoderz.prophiusassessmentprepup.comments.service;

import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentResponse;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentService {

    private final CommentOperationsFacade commentOperations;

    public CommentResponse saveComment(CommentRequest commentRequest) {
        return commentOperations.saveComments(commentRequest);
    }

    public CommentResponse updateComment(CommentUpdateRequest commentRequest) {
        return commentOperations.updateComment(commentRequest);
    }
}
