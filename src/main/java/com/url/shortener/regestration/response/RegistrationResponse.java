package com.url.shortener.regestration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponse {
    private String token;
    private RegistrationError error;


    public enum RegistrationError {
        success,
        usernameAlreadyExists,
        passwordToWeek,
        usernameAlreadyExistsAndPasswordToWeek
    }
    public static RegistrationResponse success(String token) {
        return builder().token(token).error(RegistrationError.success).build();
    }
    public static RegistrationResponse failure(RegistrationError error) {
        return builder().token(null).error(error).build();
    }
}
