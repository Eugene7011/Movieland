package com.podzirei.movieland.logging;

import com.podzirei.movieland.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class UserRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.clear();
        UUID requestId = UUID.randomUUID();
        String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            MDC.put("requestId", String.valueOf(requestId));
            MDC.put("userEmail", "guest");
            return true;
        }
        String email = request.getUserPrincipal().getName();
        int userId = CustomUserDetailsService.userNames.get(email).getId();

        MDC.put("requestId", String.valueOf(requestId));
        MDC.put("userEmail", email);
        MDC.put("userId", String.valueOf(userId));

        return true;
    }
}
