package com.github.tesis.queryms.constants

object Routes {
    const val HEALTH_CHECK = "/health-check"

    object Query {
        const val V1 = "/v1"
        const val QUERY = "/query"
        const val LIST_QUERIES = "/list"
        const val GET_BY_CODE = "/{query_code}"
        const val GET_BY_INTEGRATION_CODE = "/integration/{integration_code}"
        const val GET_BY_CODE_AND_INTEGRATION_CODE = "${GET_BY_CODE}$GET_BY_INTEGRATION_CODE"
    }
}
