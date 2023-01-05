package com.podzirei.movieland.security;

import com.podzirei.movieland.security.dto.AuthenticationRequest;
import com.podzirei.movieland.security.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        if (userDetails != null) {
            return new AuthenticationResponse(jwtUtils.generateToken(userDetails),
                    CustomUserDetailsService.userNames.get(request.getEmail()));
        }
        throw new AuthenticationCredentialsNotFoundException("Error during authentication");
    }
}
