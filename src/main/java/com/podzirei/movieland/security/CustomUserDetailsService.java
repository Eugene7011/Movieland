package com.podzirei.movieland.security;

import com.podzirei.movieland.dto.UserDto;
import com.podzirei.movieland.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
@Getter
@Setter
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    public final static Map<String, UserDto> userNames = new HashMap<>();
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userService.findUserByEmail(email);
        userNames.put(email, user);

        return new User(
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getType())));
    }
}
