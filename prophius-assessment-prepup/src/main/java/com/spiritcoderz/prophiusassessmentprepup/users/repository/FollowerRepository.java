package com.spiritcoderz.prophiusassessmentprepup.users.repository;


import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Integer> {
    @Query(value = "select f from Followers f where f.followerId = :followerId and f.userId = :userId")
    Optional<Followers> findDuplicateRequest(int userId, int followerId);
}
