package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.service.components.UserCacheManagementComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityManager {

    private final UserRepository userRepository;

    private final CacheManager cacheManager;

    public User saveUser(User user){
        return userRepository.save(user);
    }


    public Optional<User> getUserByEmail(String email){
       return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public List<String> selectDistinctEmails() {
        return userRepository.selectDistinctEmails();
    }

    public List<Integer> selectDistinctUserIds() {
        return userRepository.selectDistinctUserIds();
    }
}
