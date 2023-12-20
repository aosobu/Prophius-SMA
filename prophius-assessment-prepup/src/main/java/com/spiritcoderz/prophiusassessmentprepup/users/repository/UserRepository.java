package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = """
      select DISTINCT email from User
      """)
    List<String> selectDistinctEmails();

    @Query(value = """
      select DISTINCT id from User
      """)
    List<Integer> selectDistinctUserIds();
}
