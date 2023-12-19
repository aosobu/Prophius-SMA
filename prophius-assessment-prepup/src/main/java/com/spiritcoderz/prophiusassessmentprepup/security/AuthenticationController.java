package com.spiritcoderz.prophiusassessmentprepup.security;

import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationRequest;
import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationResponse;
import com.spiritcoderz.prophiusassessmentprepup.security.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
