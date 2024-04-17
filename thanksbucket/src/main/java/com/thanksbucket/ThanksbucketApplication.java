package com.thanksbucket;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class ThanksbucketApplication {
    @Value("${spring.profiles.active}")
    String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(ThanksbucketApplication.class, args);
    }

    @PostConstruct
    private void start() {
        log.info("{} Server Start", activeProfile);
    }
}
