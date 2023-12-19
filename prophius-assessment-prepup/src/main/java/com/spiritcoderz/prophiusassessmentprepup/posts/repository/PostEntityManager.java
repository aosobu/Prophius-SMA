package com.spiritcoderz.prophiusassessmentprepup.posts.repository;

import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.posts.service.PostDocumentCreator;
import com.spiritcoderz.prophiusassessmentprepup.posts.utils.PostDocumentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostEntityManager {

    private final PostRepository postRepository;
    private final PostDocumentCreator postDocumentCreator;


    public Post savePost(Post post){
        return postRepository.save(post);
    }


    public Optional<Post> getPost(Integer id){

        if( postDocumentCreator.canRetrievePostFromCache(id) ){

            return postDocumentCreator.getPost(id);

        }else{
            Optional<Post> retrievedPost = postRepository.findById(id);

            if(retrievedPost.isPresent()){
                Optional<Post> enrichedPost = postDocumentCreator.updatePostWithLikeCount(retrievedPost.get());
                return enrichedPost;
            }
        }

        return Optional.empty();
    }

    public Page<Post> getNextPage(int offset, int pageSize){
        return postRepository.findAll(PageRequest.of(offset, pageSize));
    }
}
