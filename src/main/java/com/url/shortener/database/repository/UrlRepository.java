package com.url.shortener.database.repository;

import com.url.shortener.database.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findByShortUrl(String shortUrl);

    List<UrlEntity> findAllByUserUsername(String username);

    @Query("SELECT u.shortUrl FROM UrlEntity u WHERE u.shortUrl = :shortUrl")
    String findEntityByShortUrl(@Param("shortUrl") String shortUrl);

}
