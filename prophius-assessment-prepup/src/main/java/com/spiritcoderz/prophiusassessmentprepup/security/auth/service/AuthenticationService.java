package com.spiritcoderz.prophiusassessmentprepup.security.auth.service;

import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationRequest;
import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationOperations authenticationOperations;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        return authenticationOperations.authenticate(authenticationRequest);
    }
}
