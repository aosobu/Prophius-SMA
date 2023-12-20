package com.spiritcoderz.prophiusassessmentprepup.workers;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAddFollowerWorker {

    private final CacheManager cacheManager;

    private final FollowerEntityManager followerEntityManager;


    @Scheduled(cron = "*/1 * * * * *")
    public void updateUserWithFollowingAndFollowerWorker(){
        List<String> cachedUserList = new ArrayList<>();

        if(getCache(AppConstants.CACHE_NAME) != null)
            udateUserInCachedList(cachedUserList);
        }

    private void udateUserInCachedList(List<String> cachedUserList) {
        cachedUserList.parallelStream().forEach(user -> {

            User users  = getCache(AppConstants.CACHE_NAME).get(user, User.class);

            List<User> followers = followerEntityManager.findAllFollowers(users.getId());
            List<User> following = followerEntityManager.findAllFollowing(users.getId());

        });
    }


    private Cache getCache(String name){
        return cacheManager.getCache("post-like");
    }

}
