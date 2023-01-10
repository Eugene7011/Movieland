//package com.podzirei.movieland.service.impl;
//
//import com.podzirei.movieland.security.CustomUserDetailsService;
//import com.podzirei.movieland.security.JwtSecurityService;
//import com.podzirei.movieland.security.dto.AuthenticationRequest;
//import com.podzirei.movieland.security.dto.AuthenticationResponse;
//import com.podzirei.movieland.service.SecurityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
////@Service
//@RequiredArgsConstructor
//public class DefaultSecurityService implements SecurityService {
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final JwtSecurityService jwtSecurityService;
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//
//        if (userDetails != null) {
//            return new AuthenticationResponse(jwtSecurityService.generateToken(userDetails),
//                    CustomUserDetailsService.userNames.get(request.getEmail()));
//        }
//        throw new AuthenticationCredentialsNotFoundException("Error during authentication");
//    }
//}
