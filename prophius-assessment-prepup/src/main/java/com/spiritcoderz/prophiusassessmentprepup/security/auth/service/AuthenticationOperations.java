package com.spiritcoderz.prophiusassessmentprepup.security.auth.service;

import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationRequest;
import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationOperations {

    private final AuthenticationComponent authenticationComponent;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return authenticationComponent.authenticate(authenticationRequest);
    }
}
