package com.github.tesis.queryms.clients

import com.github.tesis.queryms.clients.configurations.FeignConfiguration
import com.github.tesis.queryms.constants.ExternalRoutes
import com.github.tesis.queryms.constants.HEADER_USER
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import java.util.UUID

@FeignClient(
    name = ExternalRoutes.Integration.NAME,
    url = "\${microservices.integration}", configuration = [FeignConfiguration::class]
)
interface IntegrationClient {
    @GetMapping(
        value = [ExternalRoutes.Integration.GET_INTEGRATION],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun validateIntegration(
        @RequestHeader(HEADER_USER) userCode: UUID,
        @PathVariable("integration_code") integrationCode: UUID
    ): ResponseEntity<Any>
}
