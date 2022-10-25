package com.github.tesis.queryms.domain.responses

import com.github.tesis.queryms.exception.CLIENT_EXCEPTION

data class ErrorResponse(
    val code: String = CLIENT_EXCEPTION,
    val messages: List<String>
)
