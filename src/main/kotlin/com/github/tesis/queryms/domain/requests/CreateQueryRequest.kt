package com.github.tesis.queryms.domain.requests

import com.github.tesis.queryms.domain.entities.Query
import org.apache.commons.codec.digest.DigestUtils
import java.util.UUID

data class CreateQueryRequest(
    val name: String,
    val integrationCode: UUID,
    val sql: String,
    val schema: List<Map<String, String>>,
    val json: Any
) {
    fun toQuery() = Query(
        integrationCode = integrationCode,
        name = name,
        sql = sql,
        schema = schema,
        json = json,
        checksum = DigestUtils.sha256Hex(sql)
    )
}
