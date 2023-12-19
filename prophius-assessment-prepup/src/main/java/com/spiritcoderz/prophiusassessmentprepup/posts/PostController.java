package com.spiritcoderz.prophiusassessmentprepup.posts;

import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostLikeRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostRequest;
import com.spiritcoderz.prophiusassessmentprepup.posts.api.PostResponse;
import com.spiritcoderz.prophiusassessmentprepup.posts.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest){
        return ResponseEntity.ok(postService.savePost(postRequest));
    }

    @PostMapping("/like")
    public ResponseEntity<PostResponse> like(@RequestBody PostLikeRequest postLikeRequest){
        return ResponseEntity.ok(postService.executeLikePostOPeration(postLikeRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> retrievePostById(@PathVariable Integer id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}/{content}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Integer id){
        return ResponseEntity.ok(postService.updatePost(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable Integer id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @GetMapping("/{postCacheId}/{offset}/{pagesize}")
    public ResponseEntity<PostResponse> retrievePaginatedPosts(@PathVariable int offset,
                                                               @PathVariable int pageSize){
        return ResponseEntity.ok(postService.getNextPage(offset, pageSize));
    }

}
