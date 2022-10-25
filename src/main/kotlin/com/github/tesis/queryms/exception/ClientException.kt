package com.github.tesis.queryms.exception

data class ClientException(
    val code: String = CLIENT_EXCEPTION,
    val messages: List<String>
) : RuntimeException()
