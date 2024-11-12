package com.hexagonal.entrypoint.http.dto.response

data class FieldError(
    val field: String,
    val message: String
)

data class ErrorResponse(
    val httpCode: Comparable<*>,
    val message: String,
    val internalCode: String,
    val errors: List<FieldError>? = null
)