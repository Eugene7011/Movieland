package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.request.AuthenticationRequest;
import com.podzirei.movieland.response.AuthenticationResponse;
import com.podzirei.movieland.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final SecurityService securityService;

    @PostMapping("login")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        logger.info(" Successful signing up for user: " + request.getEmail());
        return securityService.authenticate(request);
    }
}
