package com.url.shortener.error;

import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PermitAll
public class ErrorController {

    @GetMapping("/404")
    public String error404() {
        return "404";
    }

}
