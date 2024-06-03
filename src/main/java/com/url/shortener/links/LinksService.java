package com.url.shortener.links;

import com.url.shortener.database.entity.UrlEntity;
import com.url.shortener.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinksService {
    private final UrlService urlService;


    public List<UrlEntity> getAllUserLinks(String username) {
        return urlService.findAllByUserUsername(username);
    }

}
