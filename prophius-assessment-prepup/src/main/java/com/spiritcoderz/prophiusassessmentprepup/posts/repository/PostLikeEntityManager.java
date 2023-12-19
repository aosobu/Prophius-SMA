package com.spiritcoderz.prophiusassessmentprepup.posts.repository;

import com.spiritcoderz.prophiusassessmentprepup.posts.entity.PostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostLikeEntityManager {

    private final PostLikeRepository postLikeRepository;

    public PostLike savePostLike(PostLike postLike){return postLikeRepository.save(postLike);}
    public List<Integer> selectDistinctPostIds(){
        return postLikeRepository.selectDistinctIds();
    }

    public void saveAll(List<PostLike> postLikeList) {
        postLikeRepository.saveAll(postLikeList);
    }

    public int getCountForPostId(Integer postId) {
        return postLikeRepository.getCountForPostId(postId);
    }
}
