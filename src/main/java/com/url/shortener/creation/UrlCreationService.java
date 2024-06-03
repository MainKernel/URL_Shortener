package com.url.shortener.creation;

import com.url.shortener.database.entity.UrlEntity;
import com.url.shortener.database.entity.UserEntity;
import com.url.shortener.database.service.UrlService;
import com.url.shortener.database.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlCreationService {
    private final UrlService urlService;
    private final UserService userService;

    public UrlCreationResponse create(UrlCreationRequest urlCreationRequest, String username) {
        String validatedUrl = validateUrl(urlCreationRequest.getLongUrl());
        Optional<UserEntity> userByUsername = userService.getUserByUsername(username);
        if (isLinkActive(validatedUrl) && userByUsername.isPresent()) {

            UrlEntity urlEntity = UrlEntity.builder()
                    .user(userByUsername.get())
                    .shortUrl(code())
                    .longUrl(validatedUrl)
                    .visited(0)
                    .createdAt(LocalDateTime.now())
                    .expiredAt(LocalDateTime.now().plusDays(14))
                    .build();

            UrlEntity savedEntity = urlService.save(urlEntity);

            return UrlCreationResponse.builder()
                    .id(savedEntity.getId())
                    .longUrl(savedEntity.getLongUrl())
                    .shortUrl(savedEntity.getShortUrl())
                    .visited(savedEntity.getVisited())
                    .createdAt(savedEntity.getCreatedAt())
                    .expiredAt(savedEntity.getExpiredAt())
                    .ownedBy(savedEntity.getUser().getUsername())
                    .build();
        }
        return new UrlCreationResponse();
    }

    public String code(){
        String code;
        while(true){
            code = UUID.randomUUID().toString().substring(0, 6);
            if( urlService.isCodeValid(code)){
                return code;
            }
        }
    }

    private String validateUrl(String url){
        String substringHttps = url.substring(1, 8);
        String substringHttp = url.substring(1, 7);

        if((substringHttps.compareTo("https://") == 0) || substringHttp.compareTo("https//") == 0){
            return url;
        }else {
            return "https://" + url;
        }
    }

    private boolean isLinkActive(String link) {
        try {
            URL url = URI.create(link).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (Exception e) {
            log.error("{}{}", e.getMessage(), link);
            return false;
        }
    }


}
