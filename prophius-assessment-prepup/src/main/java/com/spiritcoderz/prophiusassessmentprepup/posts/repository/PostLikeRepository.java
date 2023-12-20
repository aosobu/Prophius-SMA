package com.spiritcoderz.prophiusassessmentprepup.posts.repository;

import com.spiritcoderz.prophiusassessmentprepup.posts.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {

    @Query(value = """
      select DISTINCT postId from PostLike
      """)
    List<Integer> selectDistinctIds();

    @Query(value = """
      select count(postId) from PostLike p where p.postId = :id
      """)
    Integer getCountForPostId(Integer id);
}
