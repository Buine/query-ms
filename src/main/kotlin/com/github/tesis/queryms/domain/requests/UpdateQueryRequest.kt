package com.github.tesis.queryms.domain.requests

data class UpdateQueryRequest(
    val name: String?,
    val sql: String?,
    val schema: List<Map<String, String>>?,
    val json: Any?
)
