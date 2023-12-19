package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityManager {

    private final UserRepository userRepository;


    public User saveUser(User user){

        System.out.println("about to save user");
        return userRepository.save(user);
    }


    @Cacheable(value="users", key="#email")
    public Optional<User> getUserByEmail(String email){

        System.out.println("Going to retrieve data from the database");
        return userRepository.findByEmail(email);
    }


    @Cacheable(value="users", key="#id")
    public Optional<User> getUserById(Integer id){

        System.out.println("Going to retrieve data from the database");
        return userRepository.findById(id);
    }
}
