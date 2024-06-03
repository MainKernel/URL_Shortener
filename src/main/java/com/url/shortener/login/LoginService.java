package com.url.shortener.login;

import com.url.shortener.config.security.JwtService;
import com.url.shortener.database.entity.UserEntity;
import com.url.shortener.database.service.UserService;
import com.url.shortener.login.request.LoginRequest;
import com.url.shortener.login.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));
            Optional<UserEntity> byUsername = userService.getUserByUsername(loginRequest.getUsername());
            if (byUsername.isPresent()) {
                return LoginResponse.success(jwtService.generateToken(byUsername.get()));
            }
        } catch (DisabledException | LockedException | BadCredentialsException exception) {
            log.error(exception.getMessage());
        }
        return LoginResponse.badCredentials();
    }
}
