package com.spiritcoderz.prophiusassessmentprepup.users.service.components;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.users.api.FollowRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowComponent {

    private final FollowerEntityManager followerEntityManager;

    public UserResponse execute(FollowRequest followRequest, UserResponse userResponse) {

        Followers follower = buildFollowerFrom( followRequest );
        Followers savedFollowRequest = followerEntityManager.saveFollowRequest(follower);

        if( savedFollowRequest != null && isIdComparisonSuccess( savedFollowRequest, follower)) {
            userResponse.setMessage(AppConstants.FOLLOW_REQUEST_SUCCESS);
        }else{
            userResponse.setMessage(AppConstants.FOLLOW_REQUEST_FAILURE);
        }

        return userResponse;
    }

    public Followers buildFollowerFrom(FollowRequest followRequest){
        return Followers
                .builder()
                .followerId( followRequest.getFollowerId() )
                .userId( followRequest.getUserId() )
                .build();
    }

    private boolean isIdComparisonSuccess(Followers savedFollowRequest, Followers follower){
        return savedFollowRequest.getFollowerId().equals(follower.getFollowerId());
    }
}
