package com.spiritcoderz.prophiusassessmentprepup.security.filter;

import com.spiritcoderz.prophiusassessmentprepup.commons.config.AppConstants;
import com.spiritcoderz.prophiusassessmentprepup.commons.wrappers.BeanWrapper;
import com.spiritcoderz.prophiusassessmentprepup.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationProvider implements JwtAuthenticationManager {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    private BeanWrapper beanWrapper;
    public void authenticate(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwt;
        final String userEmail;
        final String authenticationHeader = getHeaderValue(request, AppConstants.AUTHORIZATION_HEADER_VALUE);

        if( isNullAuthenticationHeader(authenticationHeader) ||
                !isAuthenticationHeaderStartsWithBearer(authenticationHeader) ){

            continueFilterChain(request, response, filterChain);
            return;
        }

        jwt = extractJwtToken(authenticationHeader);
        userEmail = jwtService.getUsername(jwt);

        if( isValidUserEmail(userEmail) && isEmptySecurityContext() ) {

            UserDetails userDetails = getUserDetails(userEmail);
            setSecurityContextIfTokenValid(request, userDetails, jwt);
        }

        continueFilterChain(request, response, filterChain);
    }

    private UserDetails getUserDetails(String userEmail) {
        return userDetailsService.loadUserByUsername(userEmail);
    }

    private void setSecurityContextIfTokenValid(HttpServletRequest request,
                                                UserDetails userDetails,
                                                String jwt) {

        if (jwtService.isTokenValid(jwt, userDetails)) {

            UsernamePasswordAuthenticationToken authToken
                    = beanWrapper.getAuthenticationTokenBean(userDetails);
            authToken.setDetails(beanWrapper.getAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private boolean isEmptySecurityContext() {
         return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean isValidUserEmail(String userEmail) {
        return userEmail != null;
    }

    private String extractJwtToken(String authenticationHeader) {
        return authenticationHeader.substring(7);
    }

    private String getHeaderValue(HttpServletRequest request, String headerKey){
        return request.getHeader(headerKey);
    }

    private boolean isAuthenticationHeaderStartsWithBearer(String authenticationHeader) {
        return authenticationHeader.startsWith(AppConstants.AUTHORIZATION_HEADER_PREFIX);
    }

    private boolean isNullAuthenticationHeader(String authenticationHeader) {
        return authenticationHeader == null;
    }

    public void continueFilterChain(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}
