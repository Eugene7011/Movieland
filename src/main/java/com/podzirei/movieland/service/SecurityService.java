package com.podzirei.movieland.service;

import com.podzirei.movieland.security.dto.AuthenticationRequest;
import com.podzirei.movieland.security.dto.AuthenticationResponse;

public interface SecurityService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
