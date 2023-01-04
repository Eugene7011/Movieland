package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.UserDto;
import com.podzirei.movieland.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
