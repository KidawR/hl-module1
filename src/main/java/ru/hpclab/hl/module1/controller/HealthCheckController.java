package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/healthcheck")
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthEndpoint healthEndpoint;

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Status status = healthEndpoint.health().getStatus();

        boolean isHealthy = Status.UP.equals(status);

        Map<String, Object> response = new HashMap<>();

        response.put("overallStatus", status.getCode());
        response.put("isHealthy", isHealthy);

        return new ResponseEntity<>(response, isHealthy ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE);
    }
}