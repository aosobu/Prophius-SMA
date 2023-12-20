package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.FollowRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
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

        Followers follower = buildFollowerFrom( followRequest );

        boolean isDuplicateRequest = checkDuplicateRequest(followRequest);

        if( !isDuplicateRequest ) {
            UserDTO updatedUser =  updateFollowList(followRequest.getUserId(), followRequest.getFollowerId());
            response.setUserDTO(updatedUser);
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

    private UserDTO updateFollowList(Integer userId, Integer followerId) {

        Optional<User> user = userEntityManagerWrapper.getUserById(userId);
        Optional<User> follower = userEntityManagerWrapper.getUserById(followerId);

        UserDTO updatedUser = new UserDTO();
        List<UserDTO> followerList = new ArrayList<>();

        if(user.isPresent() && follower.isPresent()){

            updatedUser = userMapper.userToUserDTO(user.get());

            List<UserDTO> followers = updatedUser.getFollowing();
            followerList = new ArrayList<>();
            UserDTO followUserDTO = userMapper.userToUserDTO(follower.get());

            if(updatedUser.getFollowing() == null){

                followerList = new ArrayList<>();
                followerList.add(followUserDTO);

            }else{
                followerList = updatedUser.getFollowers();
                if(!followerList.contains(followUserDTO)){
                    followerList.add(followUserDTO);
                }
            }

        }

        updatedUser.setFollowers(followerList);

        return updatedUser;
    }

    public Followers buildFollowerFrom(FollowRequest followRequest){
        return Followers
                .builder()
                .followerId( followRequest.getFollowerId() )
                .userId( followRequest.getUserId() )
                .build();
    }
}
