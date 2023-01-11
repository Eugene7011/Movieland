package com.podzirei.movieland.service;

import com.podzirei.movieland.request.AuthenticationRequest;
import com.podzirei.movieland.response.AuthenticationResponse;

public interface SecurityService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
