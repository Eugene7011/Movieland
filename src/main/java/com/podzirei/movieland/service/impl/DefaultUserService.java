package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.UserDto;
import com.podzirei.movieland.exception.UserNotFoundException;
import com.podzirei.movieland.mapper.UserMapper;
import com.podzirei.movieland.repository.JpaUserRepository;
import com.podzirei.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findUserByEmail(String email) {
        return userMapper.toUserDto(jpaUserRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)));
    }
}
