package com.thanksbucket.ui.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/api/health")
    public ResponseEntity<String> health() {
        log.info("health check");
        return ResponseEntity.ok("HEALTH OK");
    }
}
