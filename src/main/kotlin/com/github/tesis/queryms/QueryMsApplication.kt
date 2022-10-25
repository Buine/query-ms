package com.github.tesis.queryms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class QueryMsApplication

fun main(args: Array<String>) {
    runApplication<QueryMsApplication>(*args)
}
