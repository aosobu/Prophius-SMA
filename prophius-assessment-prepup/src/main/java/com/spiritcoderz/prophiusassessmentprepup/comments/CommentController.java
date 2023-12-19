package com.spiritcoderz.prophiusassessmentprepup.comments;

import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentResponse;
import com.spiritcoderz.prophiusassessmentprepup.comments.api.CommentUpdateRequest;
import com.spiritcoderz.prophiusassessmentprepup.comments.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> comment(@Valid @RequestBody CommentRequest commentRequest){
       return ResponseEntity.ok(commentService.saveComment(commentRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<CommentResponse> updateComment(@Valid @RequestBody CommentUpdateRequest commentRequest){
        return ResponseEntity.ok(commentService.updateComment(commentRequest));
    }

}
