package com.thanksbucket.ui.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;


    @GetMapping("/")
    public ResponseEntity<Object> home() {
        log.debug("redirect to swagger");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(swaggerPath));
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(httpHeaders)
                .build();
    }

    @GetMapping("/api/health")
    public ResponseEntity<String> health() {
        log.info("health check");
        return ResponseEntity.ok("HEALTH OK");
    }
}
