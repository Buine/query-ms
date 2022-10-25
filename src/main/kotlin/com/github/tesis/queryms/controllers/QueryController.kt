package com.github.tesis.queryms.controllers

import com.github.tesis.queryms.constants.HEADER_USER
import com.github.tesis.queryms.constants.Routes
import com.github.tesis.queryms.domain.requests.CreateQueryRequest
import com.github.tesis.queryms.domain.requests.SearchQueriesWithArrayRequest
import com.github.tesis.queryms.domain.requests.UpdateQueryRequest
import com.github.tesis.queryms.domain.responses.BasicQueryResponse
import com.github.tesis.queryms.domain.responses.DetailQueryResponse
import com.github.tesis.queryms.services.QueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("${Routes.Query.V1}${Routes.Query.QUERY}")
class QueryController {

    @Autowired
    lateinit var queryService: QueryService

    @PostMapping
    fun createQuery(
        @RequestBody createQueryRequest: CreateQueryRequest,
        @RequestHeader(HEADER_USER) userCode: UUID
    ): ResponseEntity<BasicQueryResponse> {
        return ResponseEntity(queryService.createQuery(createQueryRequest, userCode), HttpStatus.CREATED)
    }

    @PostMapping(Routes.Query.LIST_QUERIES)
    fun listQueriesIntoArray(
        @RequestHeader(HEADER_USER) userCode: UUID,
        @RequestBody searchQueriesWithArrayRequest: SearchQueriesWithArrayRequest
    ): ResponseEntity<List<BasicQueryResponse>> =
        ResponseEntity(queryService.listQueriesWithArray(searchQueriesWithArrayRequest, userCode), HttpStatus.OK)

    @GetMapping(value = [Routes.Query.GET_BY_INTEGRATION_CODE])
    fun listQueries(
        @RequestHeader(HEADER_USER) userCode: UUID,
        @PathVariable("integration_code") integrationCode: UUID
    ): ResponseEntity<List<BasicQueryResponse>> =
        ResponseEntity(queryService.listIntegrations(integrationCode, userCode), HttpStatus.OK)

    @GetMapping(value = [Routes.Query.GET_BY_CODE_AND_INTEGRATION_CODE])
    fun getQuery(
        @RequestHeader(HEADER_USER) userCode: UUID,
        @PathVariable("query_code") queryCode: UUID,
        @PathVariable("integration_code") integrationCode: UUID,
        @RequestParam("with_json") withJson: Boolean?
    ): ResponseEntity<DetailQueryResponse> =
        ResponseEntity(queryService.getQueryDetail(withJson, queryCode, integrationCode, userCode), HttpStatus.OK)

    @PatchMapping(value = [Routes.Query.GET_BY_CODE_AND_INTEGRATION_CODE])
    fun updateQuery(
        @RequestHeader(HEADER_USER) userCode: UUID,
        @PathVariable("query_code") queryCode: UUID,
        @PathVariable("integration_code") integrationCode: UUID,
        @RequestBody updateQueryRequest: UpdateQueryRequest,
    ): ResponseEntity<DetailQueryResponse> =
        ResponseEntity(queryService.updateIntegration(updateQueryRequest, queryCode, integrationCode, userCode), HttpStatus.OK)

    @DeleteMapping(value = [Routes.Query.GET_BY_CODE_AND_INTEGRATION_CODE])
    fun deleteQuery(
        @RequestHeader(HEADER_USER) userCode: UUID,
        @PathVariable("query_code") queryCode: UUID,
        @PathVariable("integration_code") integrationCode: UUID
    ): ResponseEntity<DetailQueryResponse> =
        ResponseEntity(queryService.deleteIntegration(queryCode, integrationCode, userCode), HttpStatus.OK)
}
