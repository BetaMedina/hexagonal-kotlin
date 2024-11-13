package com.hexagonal.infra.database.elastic.dto

data class SearchCriteriaDbDTO(
    val query: String? ,
    val size: Int = 10,
    val from: Int = 0,
    val sort: List<SortField>? = null,
    val filters: Map<String, Any>? = null
)

data class SortField(
    val field: String,
    val order: SortOrder = SortOrder.ASC
)

enum class SortOrder {
    ASC, DESC
}