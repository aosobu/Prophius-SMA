package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCacheManagementComponent {

    private final CacheManager cacheManager;

    private final String cacheName = AppConstants.CACHE_NAME;


    public boolean addUserToCache(User savedUser) {

        if(getCache(cacheName) != null) {

            addEmailToCacheList(savedUser, cacheName);
            getCache(cacheName).put( getUserEmailKey( savedUser ), savedUser);
            getCache(cacheName).put( getUserIdKey( savedUser.getId() ) , savedUser );
        }

        return isUserInCache(getUserEmailKey(savedUser )) &&  isUserInCache(getUserIdKey( savedUser.getId() ));
    }

    public boolean isUserInCache(String key){
        return getCache(cacheName).get(key) != null;
    }

    private void addEmailToCacheList(User savedUser, String cacheName) {

        List<String> userList = getCache(cacheName).get(AppConstants.USER_LIST_KEY, ArrayList.class);

        if(userList == null){
            userList = new ArrayList<>();
            userList.add(savedUser.getEmail());
            getCache(cacheName).put(AppConstants.USER_LIST_KEY, userList);
        }else{
            userList.add(savedUser.getEmail());
            getCache(cacheName).put(AppConstants.USER_LIST_KEY, userList);
        }

    }

    private String getUserEmailKey(User user){
        return getUserEmailKey(user.getEmail());
    }

    private String getUserEmailKey(String email){
        return "#"+email;
    }

    private String getUserIdKey(int id){
        return "#"+id;
    }

    private Cache getCache(String name){
        return cacheManager.getCache(name);
    }

    public Optional<User> getUserFromCache(String email) {
        User user = getCache(AppConstants.CACHE_NAME).get( getUserEmailKey(email), User.class);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    public Optional<User> getUserFromCache(int id) {
        User user = getCache(AppConstants.CACHE_NAME).get( getUserIdKey(id) , User.class);
        return user == null ? Optional.empty() : Optional.of(user);
    }
}
