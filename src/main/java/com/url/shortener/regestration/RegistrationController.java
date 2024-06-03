package com.url.shortener.regestration;

import com.url.shortener.regestration.request.RegistrationRequest;
import com.url.shortener.regestration.response.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/api/v1/registration")
    public RegistrationResponse registration(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
