package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.service.components.UserCacheManagementComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEntityManagerWrapper {

    private final UserEntityManager userEntityManager;

    private final UserCacheManagementComponent userCacheManagementComponent;

    private final CacheManager cacheManager;

    public Optional<User> getUserByEmail(String email){
        Optional<User> user = Optional.empty();
        if(cacheManager.getCache("user") != null){
            user = userCacheManagementComponent.getUserFromCache(email);
        }
        if(!user.isPresent()){
            user =  userEntityManager.getUserByEmail(email);
            userCacheManagementComponent.addUserToCache(user.get());
        }
         return user;
    }

    public Optional<User> getUserById(int id){
        Optional<User> user = Optional.empty();
        if(cacheManager.getCache("user") != null){
            user = userCacheManagementComponent.getUserFromCache(id);
        }
        if(!user.isPresent()){
            user =  userEntityManager.getUserById(id);
            userCacheManagementComponent.addUserToCache(user.get());
        }
        return user;
    }
}
