package com.github.tesis.queryms.domain.responses

import java.util.UUID

interface QueryResponse {
    val code: UUID
    val name: String
    val sql: String
}

data class BasicQueryResponse(
    override val code: UUID,
    override val name: String,
    override val sql: String
) : QueryResponse

data class DetailQueryResponse(
    override val code: UUID,
    override val name: String,
    override val sql: String,
    val schema: Any,
    val json: Any?
) : QueryResponse
