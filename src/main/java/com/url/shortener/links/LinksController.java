package com.url.shortener.links;


import com.url.shortener.database.entity.UrlEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("links")
@RequiredArgsConstructor
public class LinksController {
    private final LinksService linksService;

    @GetMapping("/api/v1/links")
    public List<UrlEntity> response() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return linksService.getAllUserLinks(name);
    }
}
