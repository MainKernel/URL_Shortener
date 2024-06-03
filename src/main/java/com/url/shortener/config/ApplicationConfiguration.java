package com.url.shortener.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.url_shortener")
@EnableCaching
public class ApplicationConfiguration {
}
