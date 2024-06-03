package com.url.shortener.login.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private LoginError loginError;

    public enum LoginError {
        success,
        badCredentials
    }

    public static LoginResponse success(String token) {
        return LoginResponse.builder().token(token).loginError(LoginError.success).build();
    }

    public static LoginResponse badCredentials() {
        return LoginResponse.builder().token(null).loginError(LoginError.badCredentials).build();
    }
}
