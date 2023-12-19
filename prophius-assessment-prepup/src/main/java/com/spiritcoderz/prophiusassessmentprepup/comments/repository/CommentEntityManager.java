package com.spiritcoderz.prophiusassessmentprepup.comments.repository;

import com.spiritcoderz.prophiusassessmentprepup.comments.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentEntityManager {

    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Integer id){
       return commentRepository.findById(id);
    }
}
