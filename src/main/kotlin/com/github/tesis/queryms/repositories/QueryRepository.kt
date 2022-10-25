package com.github.tesis.queryms.repositories

import com.github.tesis.queryms.domain.entities.Query
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface QueryRepository : JpaRepository<Query, Long> {
    fun getByIntegrationCode(userCode: UUID): List<Query>

    @org.springframework.data.jpa.repository.Query("SELECT q FROM Query q WHERE q.integrationCode = :integrationCode AND q.code IN :listCodes")
    fun getByIntegrationCodeAndIntoArrayCodes(integrationCode: UUID, listCodes: List<UUID>): List<Query>

    fun getByCodeAndIntegrationCode(code: UUID, userCode: UUID): Optional<Query>

    fun getByCode(code: UUID): Optional<Query>
}
