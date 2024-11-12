package com.hexagonal

import SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication()
@Import(SecurityConfig::class)
class HexagonalArchApplication

fun main(args: Array<String>) {
	runApplication<HexagonalArchApplication>(*args)
}
