package com.spiritcoderz.prophiusassessmentprepup.posts.repository;

import com.spiritcoderz.prophiusassessmentprepup.posts.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {

    @Query(value = """
      select DISTINCT postId from PostLike where isCounted = false
      """)
    List<Integer> selectDistinctIds();

    @Query(value = """
      select count(postId) from PostLike p where p.isCounted = false and p.postId = :id
      """)
    Integer getCountForPostId(Integer id);
}
