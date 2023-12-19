package com.spiritcoderz.prophiusassessmentprepup.commons.wrappers;

import com.spiritcoderz.prophiusassessmentprepup.security.auth.model.AuthenticationRequest;
import com.spiritcoderz.prophiusassessmentprepup.users.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Configuration
public class BeanWrapper {

    public UsernamePasswordAuthenticationToken getAuthenticationTokenBean(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()
        );
    }


    public UsernamePasswordAuthenticationToken getAuthenticationBean(
            AuthenticationRequest authenticationRequest) {
        return new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                                                    authenticationRequest.getPassword());
    }

    public WebAuthenticationDetailsSource getAuthenticationDetailsSource() {
        return new WebAuthenticationDetailsSource();
    }

    public SimpleGrantedAuthority getGrantedAuthorityBean(Role role) {
        return new SimpleGrantedAuthority(role.name());
    }

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
