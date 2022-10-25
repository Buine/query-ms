package com.github.tesis.queryms.controllers

import com.github.tesis.queryms.constants.Routes
import com.github.tesis.queryms.domain.responses.HealthCheckResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping(Routes.HEALTH_CHECK)
    fun healthCheck(): ResponseEntity<HealthCheckResponse> {
        return ResponseEntity(HealthCheckResponse(), HttpStatus.OK)
    }
}
