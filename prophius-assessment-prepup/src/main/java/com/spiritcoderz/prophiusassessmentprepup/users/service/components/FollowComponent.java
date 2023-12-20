package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.FollowRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserRecord;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.mapper.UserMapperImpl;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManagerWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FollowComponent {

    private final FollowerEntityManager followerEntityManager;

    private final UserEntityManagerWrapper userEntityManagerWrapper;

    private final UserMapperImpl userMapper;

    public UserResponse execute(FollowRequest followRequest) {
        UserResponse response = new UserResponse();

        Followers follower = buildFollowingFrom( followRequest );
        boolean isDuplicateRequest = checkDuplicateRequest( followRequest );

        if( !isDuplicateRequest ) {
            followerEntityManager.saveFollowRequest(follower);
            UserDTO responseDTO =  executeFollowOperation(followRequest.getUserId(), followRequest.getFollowerId());
            response.setUserDTO(responseDTO);
            response.setMessage(AppConstants.FOLLOW_REQUEST_SUCCESS);
        }else{
            response.setMessage(AppConstants.FOLLOW_REQUEST_FAILURE);
        }

        return response;
    }

    private boolean checkDuplicateRequest(FollowRequest followRequest) {
        Optional<Followers> existingFollower = followerEntityManager.getFollowRequest(followRequest.getUserId(), followRequest.getFollowerId());
        return  existingFollower.isPresent();
    }

    private UserDTO executeFollowOperation(Integer userId, Integer followerId) {
        UserDTO responseDTO = new UserDTO();

        Optional<User> user = getUserRecord(userId);
        Optional<User> following = getUserRecord(followerId);

        if(!user.isEmpty() && !following.isEmpty()){

            User updatedUser = updateUserFollowingListWithFollowingRecord(user.get(), following.get());
            User updatedFollowing = updateFollowerFollowListWithUserRecord(following.get(), user.get());

            updateCacheWith(updatedUser);
            updateCacheWith(updatedFollowing);

            responseDTO = userMapper.userToUserDTO(updatedUser);
        }

        return responseDTO;
    }

    private void updateCacheWith(User user) {
        userEntityManagerWrapper.updateCache(user);
    }

    private Optional<User> getUserRecord(Integer id) {
        return userEntityManagerWrapper.getUserById(id);
    }

    private User updateUserFollowingListWithFollowingRecord(User user, User following) {
        List<UserRecord> followingList = new ArrayList<>();
        UserRecord followingRecord = new UserRecord(following.getId(), following.getUsername(),
                                                following.getEmail(), following.getProfilePictureFilePath());
        followingList.add(followingRecord);
        if(!hasExistingFollowing(user)){
            followingList.addAll(user.getFollowing());
            user.setFollowing(followingList);
        }else {
            user.setFollowing(new ArrayList<>());
            user.setFollowing(followingList);
        }

        return user;
    }

    public boolean hasExistingFollowing(User user){
        return user.getFollowing() == null;
    }

    private User updateFollowerFollowListWithUserRecord(User following, User user) {
        List<UserRecord> followerList = new ArrayList<>();
        UserRecord followerRecord = new UserRecord(user.getId(), user.getUsername(),
                user.getEmail(), user.getProfilePictureFilePath());

        followerList.add(followerRecord);
        if(!hasExistingFollowers(following)){
            followerList.addAll(following.getFollowers());
            following.setFollowing(followerList);
        }else{
            following.setFollowers(null);
            following.setFollowers(followerList);
        }

        return following;
    }

    public boolean hasExistingFollowers(User following){
        return following.getFollowers() == null;
    }

    public Followers buildFollowingFrom(FollowRequest followRequest){
        return Followers
                .builder()
                .followerId( followRequest.getFollowerId() )
                .userId( followRequest.getUserId() )
                .build();
    }
}
