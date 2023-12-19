package com.spiritcoderz.prophiusassessmentprepup.users.repository;

import com.spiritcoderz.prophiusassessmentprepup.users.dto.FollowerDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.mapper.UserMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerEntityManager {

    private final FollowerRepository followerRepository;

    private final UserMapperImpl userMapper;

    public List<Object> findAllFollowers(Integer id) {
        return followerRepository.findAllFollowers(id);
    }

    public List<Object> findAllFollowing(Integer id) {
        return followerRepository.findAllFollowing(id);
    }

    public Followers saveFollowRequest(Followers followers){
        return followerRepository.save(followers);
    }
}
