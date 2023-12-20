package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.FollowerDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Integer> {

    @Query(value = """
      select u.id as id, u.username as userName, u.email as email,
       u.profilePictureFilePath as profilePicture from User u where u.id in
        (select f.followerId from Followers f where f.userId = :id)
      """)
    List<User> findAllFollowers(Integer id);

    @Query(value = """
      select u.id as id, u.username as userName, u.email as email, 
      u.profilePictureFilePath as profilePicture from User u where u.id in
        (select f.userId from Followers f where f.followerId = :id)
      """)
    List<User> findAllFollowing(Integer id);

    @Query(value = """
      select f from Followers f where f.followerId = :followerId and f.userId = :userId
      """)
    Optional<Followers> findDuplicateRequest(int userId, int followerId);
}
