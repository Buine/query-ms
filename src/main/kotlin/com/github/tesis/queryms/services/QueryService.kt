package com.github.tesis.queryms.services

import com.github.tesis.queryms.clients.IntegrationClient
import com.github.tesis.queryms.domain.requests.CreateQueryRequest
import com.github.tesis.queryms.domain.requests.SearchQueriesWithArrayRequest
import com.github.tesis.queryms.domain.requests.UpdateQueryRequest
import com.github.tesis.queryms.domain.responses.BasicQueryResponse
import com.github.tesis.queryms.domain.responses.DetailQueryResponse
import com.github.tesis.queryms.exception.ClientException
import com.github.tesis.queryms.repositories.QueryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class QueryService {

    @Autowired
    lateinit var queryRepository: QueryRepository

    @Autowired
    lateinit var integrationClient: IntegrationClient

    fun createQuery(createQueryRequest: CreateQueryRequest, userCode: UUID): BasicQueryResponse {
        integrationClient.validateIntegration(userCode, createQueryRequest.integrationCode)
        val query = queryRepository.save(createQueryRequest.toQuery())
        return query.toBasicQueryResponse()
    }

    fun listQueriesWithArray(searchQueriesWithArrayRequest: SearchQueriesWithArrayRequest, userCode: UUID): List<BasicQueryResponse> {
        integrationClient.validateIntegration(userCode, searchQueriesWithArrayRequest.integrationCode)
        val queries = queryRepository.getByIntegrationCodeAndIntoArrayCodes(searchQueriesWithArrayRequest.integrationCode, searchQueriesWithArrayRequest.queries)
        if (searchQueriesWithArrayRequest.queries.size != queries.size) {
            throw ClientException("ERROR_SEARCH_QUERIES", listOf("Error while search into queries, maybe one or more queries not exists"))
        }
        return queries.map {
            it.toBasicQueryResponse()
        }
    }

    fun listIntegrations(integrationCode: UUID, userCode: UUID): List<BasicQueryResponse> {
        integrationClient.validateIntegration(userCode, integrationCode)
        val queries = queryRepository.getByIntegrationCode(integrationCode)
        return queries.map {
            it.toBasicQueryResponse()
        }
    }

    fun getQueryDetail(withJson: Boolean?, queryCode: UUID, integrationCode: UUID, userCode: UUID): DetailQueryResponse {
        integrationClient.validateIntegration(userCode, integrationCode)
        val query = queryRepository.getByCodeAndIntegrationCode(queryCode, integrationCode).orElseThrow {
            ClientException("QUERY_NOT_EXISTS", listOf("Query not exists or no is in this integration"))
        }

        return query.toDetailQueryResponse(withJson ?: true)
    }

    fun updateIntegration(updateQueryRequest: UpdateQueryRequest, queryCode: UUID, integrationCode: UUID, userCode: UUID): DetailQueryResponse {
        integrationClient.validateIntegration(userCode, integrationCode)
        val query = queryRepository.getByCodeAndIntegrationCode(queryCode, integrationCode).orElseThrow {
            ClientException("QUERY_NOT_EXISTS", listOf("Query not exists or no is in this integration"))
        }

        query.updateFromUpdateRequest(updateQueryRequest)
        queryRepository.save(query)

        return query.toDetailQueryResponse(true)
    }

    fun deleteIntegration(queryCode: UUID, integrationCode: UUID, userCode: UUID): DetailQueryResponse {
        integrationClient.validateIntegration(userCode, integrationCode)
        val query = queryRepository.getByCodeAndIntegrationCode(queryCode, integrationCode).orElseThrow {
            ClientException("QUERY_NOT_EXISTS", listOf("Query not exists or no is in this integration"))
        }

        queryRepository.delete(query)
        return query.toDetailQueryResponse(true)
    }
}
