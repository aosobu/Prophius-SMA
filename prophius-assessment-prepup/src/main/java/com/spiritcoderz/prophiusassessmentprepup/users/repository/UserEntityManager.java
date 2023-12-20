package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
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

    public List<User> findAllFollowers(Integer id) {
        return userRepository.findAllFollowers(id);
    }

    public List<UserDTO> findAllFollowing(Integer id) {
        return userRepository.findAllFollowing(id);
    }

    public List<String> selectDistinctEmails() {
        return userRepository.selectDistinctEmails();
    }

    public List<Integer> selectDistinctUserIds() {
        return userRepository.selectDistinctUserIds();
    }
}
