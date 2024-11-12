package com.hexagonal.entrypoint.http.dto.response

data class PaginatedResult<T>(
    val content: List<T>?,
    val totalElements: Int?,
    val totalPages: Int?,
    val currentPage: Int?,
    val pageSize: Int?,
    val isFirst: Boolean?,
    val isLast: Boolean?
)