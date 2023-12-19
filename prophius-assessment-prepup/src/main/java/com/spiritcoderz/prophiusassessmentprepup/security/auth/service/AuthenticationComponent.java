package com.spiritcoderz.prophiusassessmentprepup.security.auth.service;

import com.spiritcoderz.prophiusassessmentprepup.commons.wrappers.BeanWrapper;
import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationRequest;
import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationResponse;
import com.spiritcoderz.prophiusassessmentprepup.security.service.JwtService;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationComponent {
    private final AuthenticationManager authenticationManager;
    private final BeanWrapper beanWrapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(beanWrapper.getAuthenticationBean(request));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.getToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
