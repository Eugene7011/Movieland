//package com.podzirei.movieland.web.controller;
//
//import com.podzirei.movieland.security.dto.AuthenticationRequest;
//import com.podzirei.movieland.security.dto.AuthenticationResponse;
//import com.podzirei.movieland.service.SecurityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final SecurityService securityService;
//
//    @PostMapping("login")
//    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
//        return securityService.authenticate(request);
//    }
//}
