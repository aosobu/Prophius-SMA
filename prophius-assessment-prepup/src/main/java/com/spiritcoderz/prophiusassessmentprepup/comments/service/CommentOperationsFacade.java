package com.spiritcoderz.prophiusassessmentprepup.comments.service;

import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentResponse;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentUpdateRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.service.component.CommentComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentOperationsFacade {

    private final CommentComponent commentSaveComponet;

    public CommentResponse saveComments(CommentRequest commentRequest) {
        return commentSaveComponet.executeSave(commentRequest, new CommentResponse());
    }

    public CommentResponse updateComment(CommentUpdateRequest commentRequest) {
        return commentSaveComponet.executeUpdate(commentRequest, new CommentResponse());
    }
}
