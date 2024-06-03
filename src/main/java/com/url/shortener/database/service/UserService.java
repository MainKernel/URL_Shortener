package com.url.shortener.database.service;

import com.url.shortener.config.security.JwtService;
import com.url.shortener.database.entity.UserEntity;
import com.url.shortener.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users")
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @CachePut(value = "users")
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
