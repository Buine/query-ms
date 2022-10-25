package com.github.tesis.queryms.domain.entities

import com.github.tesis.queryms.domain.enums.StatusEnum
import com.github.tesis.queryms.domain.requests.UpdateQueryRequest
import com.github.tesis.queryms.domain.responses.BasicQueryResponse
import com.github.tesis.queryms.domain.responses.DetailQueryResponse
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "query")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
open class Query(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long = 0,

    @Column(name = "code")
    open var code: UUID = UUID.randomUUID(),

    @Column(name = "integration_code")
    open var integrationCode: UUID,

    @Column(name = "name")
    open var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    open var status: StatusEnum = StatusEnum.SUCCESSFULLY,

    @Column(name = "sql")
    open var sql: String,

    @Column(name = "checksum")
    open var checksum: String,

    @Column(name = "created_at")
    open var createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    open var updatedAt: Instant = Instant.now(),

    @Column(name = "schema", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    open var schema: List<Map<String, String>>,

    @Column(name = "json", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    open var json: Any,
) {
    fun toBasicQueryResponse() = BasicQueryResponse(
        code = code,
        name = name,
        sql = sql
    )

    fun toDetailQueryResponse(withJsonQuery: Boolean) = DetailQueryResponse(
        code = code,
        name = name,
        sql = sql,
        schema = schema,
        json = if (withJsonQuery) { json } else { null }
    )

    fun updateFromUpdateRequest(updateRequest: UpdateQueryRequest) {
        name = updateRequest.name ?: name
        sql = updateRequest.sql ?: sql
        schema = updateRequest.schema ?: schema
        json = updateRequest.json ?: json
    }
}
