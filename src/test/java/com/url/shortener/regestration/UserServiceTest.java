package com.url.shortener.regestration;

import com.url.shortener.configuration.IT;
import com.url.shortener.configuration.IntegrationTestsDatabase;
import com.url.shortener.database.entity.UserEntity;
import com.url.shortener.database.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;


@IT
@ExtendWith(SpringExtension.class)
@DirtiesContext
@EnableCaching
class UserServiceTest extends IntegrationTestsDatabase {
    @Mock
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;


    @Test
    public void testThatUserFoundInDatabase() {
        Optional<UserEntity> johnDoe = userService.getUserByUsername("john_doe");
        Assertions.assertNotNull(johnDoe);
    }

    @Test
    public void testThatUserNotFoundInDatabase() {
        Assertions.assertTrue(userService.getUserByUsername("not_existing_user").isEmpty());
    }

    @Test
    public void testThatCachingOfUsersWork() {
        Optional<UserEntity> johnDoe = userService.getUserByUsername("john_doe");
        Assertions.assertEquals(johnDoe, Optional.ofNullable(cacheManager.getCache("users"))
                .map(c -> c.get("john_doe", UserEntity.class)));
    }


    @Test
    public void testUserSave() {
        UserEntity userEntity1 = userService.saveUser(UserEntity.builder()
                .username("test")
                .password("$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS")
                .role(UserEntity.ServerRole.USER)
                .build());

        Optional<UserEntity> test = userService.getUserByUsername("test");
        Assertions.assertEquals(userEntity1, test.orElse(null));
    }
}