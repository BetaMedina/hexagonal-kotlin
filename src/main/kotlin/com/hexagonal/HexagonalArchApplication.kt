package com.hexagonal

import SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@SpringBootApplication()
@Import(SecurityConfig::class)
@EnableElasticsearchRepositories(basePackages = ["com.hexagonal.infra.database.elastic.repository.interfaces"])
class HexagonalArchApplication

fun main(args: Array<String>) {
	runApplication<HexagonalArchApplication>(*args)
}
