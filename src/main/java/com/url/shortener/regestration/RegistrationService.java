package com.url.shortener.regestration;

import com.url.shortener.config.security.JwtService;
import com.url.shortener.database.entity.UserEntity;
import com.url.shortener.database.service.UserService;
import com.url.shortener.regestration.request.RegistrationRequest;
import com.url.shortener.regestration.response.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        return validateRegistration(registrationRequest);
    }

    private RegistrationResponse validateRegistration(RegistrationRequest registrationRequest) {
        boolean isUsernameOk = isUsernameExists(registrationRequest.getUsername());
        boolean isPasswordOk = isPasswordOk(registrationRequest.getPassword());
        RegistrationResponse registrationResponse;

        if (!isUsernameOk && !isPasswordOk) {
            registrationResponse = RegistrationResponse
                    .failure(RegistrationResponse.RegistrationError.usernameAlreadyExistsAndPasswordToWeek);
        } else if (!isUsernameOk) {
            registrationResponse =
            RegistrationResponse.failure(RegistrationResponse.RegistrationError.usernameAlreadyExists);
        } else if (!isPasswordOk) {
            registrationResponse =
            RegistrationResponse.failure(RegistrationResponse.RegistrationError.passwordToWeek);
        } else {
            registrationResponse = getRegistrationResponse(registrationRequest);
        }

        return registrationResponse;
    }

    private RegistrationResponse getRegistrationResponse(RegistrationRequest registrationRequest) {
        RegistrationResponse registrationResponse;
        UserEntity newUser  = UserEntity.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
        userService.saveUser(newUser);
        registrationResponse = RegistrationResponse.success(jwtService.generateToken(newUser));
        return registrationResponse;
    }

    private boolean isPasswordOk(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z0-9]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }

    private boolean isUsernameExists(String username) {
        return userService.getUserByUsername(username).isEmpty();
    }

}
