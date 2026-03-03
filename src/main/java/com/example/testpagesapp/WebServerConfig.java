package com.example.testpagesapp;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class WebServerConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            // Register our custom status code 678
            factory.addErrorPages(new org.springframework.boot.web.server.ErrorPage(HttpStatus.valueOf(678), "/error-678"));
        };
    }
}