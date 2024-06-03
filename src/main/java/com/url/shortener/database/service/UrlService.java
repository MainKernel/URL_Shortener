package com.url.shortener.database.service;

import com.url.shortener.database.entity.UrlEntity;
import com.url.shortener.database.repository.UrlRepository;
import com.url.shortener.database.error.UrlNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"urls"})
@Slf4j
public class UrlService {
    private final UrlRepository urlRepository;
    private final CacheManager cacheManager;

    @CachePut
    public UrlEntity findByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException(shortUrl));
    }
    @CachePut
    public UrlEntity save(UrlEntity urlEntity) {
       return urlRepository.save(urlEntity);
    }
    @CacheEvict
    public void delete(UrlEntity urlEntity) {
        urlRepository.delete(urlEntity);
    }

    public List<UrlEntity> findAllByUserUsername(String username) {
        return urlRepository.findAllByUserUsername(username);
    }

    public boolean isCodeValid(String code) {
        String shortUrl = urlRepository.findEntityByShortUrl(code);
        return shortUrl == null || shortUrl.isEmpty();
    }

    public String getLongUrl(String shortUrl) {
        try{
            UrlEntity byShortUrl = findByShortUrl(shortUrl);

            if(validateUrlDate(byShortUrl.getExpiredAt())){
                delete(byShortUrl);
                return null;
            }

            byShortUrl.setVisited(byShortUrl.getVisited() + 1);
            save(byShortUrl);

            return byShortUrl.getLongUrl();
        }catch (UrlNotFoundException e){
            log.info(e.getMessage());
        }
        return null;
    }

    private boolean validateUrlDate(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(dateTime);
    }

}
