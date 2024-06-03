package com.url.shortener.creation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("urlCreation")
@RequiredArgsConstructor
public class CreationController {
    private final UrlCreationService urlCreationService;

    @PostMapping("/api/v1/urls/create")
    public UrlCreationResponse createUrl(@RequestBody UrlCreationRequest urlCreationRequest) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return urlCreationService.create(urlCreationRequest, name);
    }

}
