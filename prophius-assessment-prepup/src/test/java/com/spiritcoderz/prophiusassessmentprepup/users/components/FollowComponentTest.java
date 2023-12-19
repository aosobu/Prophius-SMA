package com.spiritcoderz.prophiusassessmentprepup.users.components;

import com.spiritcoderz.prophiusassessmentprepup.users.api.FollowRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.api.UserResponse;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.mapper.UserMapperImpl;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerRepository;
import com.spiritcoderz.prophiusassessmentprepup.users.service.components.FollowComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class FollowComponentTest {
    private static FollowComponent followComponent;
    @Mock
    private static FollowerEntityManager followerEntityManager;
    private static Followers savedFollower;
    private static FollowRequest followRequest;
    private final String MESSAGE = "success";

    @BeforeAll
    public static void before(){

        followRequest = FollowRequest.builder().followerId(1).userId(3).build();
        savedFollower = Followers.builder().followerId(1).userId(3).build();

        followerEntityManager = new FollowerEntityManager(Mockito.mock(FollowerRepository.class),
                Mockito.mock(UserMapperImpl.class));
        followComponent = new FollowComponent(followerEntityManager);

    }
    @Test
    public void whenFollowerRequestValid_thenFollowSave_thenSuccess(){

        Followers follower = followComponent.buildFollowerFrom(followRequest);

        Mockito
                .when(followerEntityManager.saveFollowRequest(follower))
                .thenReturn(savedFollower);

        UserResponse userResponse = followComponent.execute(followRequest, new UserResponse());

        Assertions.assertEquals(userResponse.getMessage(), MESSAGE);
    }

    @Test
    public void whenFollowerRequestValid_thenFollowSaveUnsuccessful_thenFailure(){

        Followers follower = followComponent.buildFollowerFrom(followRequest);
        savedFollower = null;

        Mockito
                .when(followerEntityManager.saveFollowRequest(follower))
                .thenReturn(savedFollower);

        UserResponse userResponse = followComponent.execute(followRequest, new UserResponse());

        Assertions.assertNotEquals(userResponse.getMessage(), MESSAGE);
    }
}
