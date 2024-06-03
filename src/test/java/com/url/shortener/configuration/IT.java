package com.url.shortener.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@Profile("test")
@PropertySource("classpath:application.properties")
public @interface IT {
}
