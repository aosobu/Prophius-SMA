package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.mapper.UserMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerEntityManager {

    private final FollowerRepository followerRepository;

    private final UserMapperImpl userMapper;

    public Followers saveFollowRequest(Followers followers){
        return followerRepository.save(followers);
    }

    public Optional<Followers> getFollowRequest(int userId, int followerId){
        return followerRepository.findDuplicateRequest(userId, followerId);
    }
}
