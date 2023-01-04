package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.UserDto;

public interface UserService {
    UserDto findUserByEmail(String email);
}
