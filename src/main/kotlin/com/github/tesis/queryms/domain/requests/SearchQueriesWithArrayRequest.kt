package com.github.tesis.queryms.domain.requests

import java.util.UUID

data class SearchQueriesWithArrayRequest(
    val integrationCode: UUID,
    val queries: List<UUID>
)
