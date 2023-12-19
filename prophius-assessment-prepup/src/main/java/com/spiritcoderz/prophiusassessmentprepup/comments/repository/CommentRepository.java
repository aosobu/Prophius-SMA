package com.spiritcoderz.prophiusassessmentprepup.comments.repository;

import com.spiritcoderz.prophiusassessmentprepup.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
