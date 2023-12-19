package com.spiritcoderz.prophiusassessmentprepup.comments.service.component;

import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentUpdateRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.model.CommentDTO;
import com.spiritcoderz.prophiusassessmentprepup.comments.repository.CommentEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentResponse;
import com.spiritcoderz.prophiusassessmentprepup.comments.entity.Comment;
import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentComponent {

    private final CommentEntityManager commentEntityManager;

    public CommentResponse executeSave(CommentRequest commentRequest,
                                       CommentResponse commentResponse) {

        Comment comment = buildComment(commentRequest);
        Comment savedComment = commentEntityManager.saveComment(comment);

        if(savedComment != null){
            commentResponse.setMessage(AppConstants.COMMENT_CREATION_SUCCESS);
        }else{
            commentResponse.setMessage(AppConstants.COMMENT_CREATION_FAILURE);
        }

        return commentResponse;
    }

    public Comment buildComment(CommentRequest commentRequest){
        return Comment
                .builder()
                .content(commentRequest.getComment())
                .userId(commentRequest.getUserId())
                .postId(commentRequest.getPostId())
                .build();
    }

    public CommentResponse executeUpdate(CommentUpdateRequest commentRequest, CommentResponse commentResponse) {

        Optional<Comment> savedComment = commentEntityManager.getCommentById(commentRequest.getCommentId());

        if(savedComment.isPresent()){
            savedComment.get().setContent(commentRequest.getComment());
            Comment updatedComment = commentEntityManager.saveComment(savedComment.get());
            updateCommentResponse(updatedComment, commentResponse);
        }

        commentResponse.setMessage(AppConstants.COMMENT_UPDATE_FAILURE);
        return commentResponse;
    }

    private CommentResponse updateCommentResponse(Comment updatedComment, CommentResponse commentResponse) {
        if(updatedComment != null){
            commentResponse.setMessage(AppConstants.COMMENT_UPDATE_SUCCESS);
            commentResponse.setCommentDTO( CommentDTO.builder().
                                                    id(updatedComment.getId())
                                                    .content(updatedComment.getContent())
                                                    .userId(updatedComment.getUserId())
                                                    .build());
        }
        return commentResponse;
    }
}
