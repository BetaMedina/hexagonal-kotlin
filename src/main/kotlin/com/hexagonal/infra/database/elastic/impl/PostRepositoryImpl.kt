package com.hexagonal.infra.database.elastic.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hexagonal.core.dtos.PaginatedResult
import com.hexagonal.core.model.PostCore
import com.hexagonal.core.port.out.PostRepositoryPort
import com.hexagonal.infra.database.elastic.config.ElasticConfig
import com.hexagonal.infra.database.elastic.dto.SearchCriteriaDbDTO
import com.hexagonal.infra.database.elastic.model.ClientDbCore
import com.hexagonal.infra.database.elastic.model.PostsDbModel
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class PostRepositoryImpl (@Value("\${spring.elasticsearch.rest.uris}") private val url:String): ElasticConfig(url, "posts-index"),PostRepositoryPort {
    override fun getAll(pageable: PageRequest): PaginatedResult<PostCore> {
        try {
            this.getDocument(
                SearchCriteriaDbDTO(
                    size = pageable.pageSize,
                    from = pageable.pageNumber * pageable.pageSize,
                    sort = null,
                    query = null
                )
            ).use { response ->
                val status = response?.statusLine?.statusCode
                val responseEntity = response?.entity
                if (status != HttpStatus.OK.value()) {
                    throw Exception("Query returned empty or unsuccessful status: $status")
                }
                val jsonResponse = EntityUtils.toString(responseEntity)
                val objectMapper = jacksonObjectMapper()
                val hitsNode = objectMapper.readTree(jsonResponse).path("hits")
                val totalElements = hitsNode.path("total").path("value").asInt()
                val hitsArray = hitsNode.path("hits")

                val content = hitsArray.map { hit ->
                    objectMapper.treeToValue(hit.path("_source"), PostCore::class.java)
                }
                return PaginatedResult(
                    totalElements = totalElements,
                    totalPages = (totalElements + pageable.pageSize - 1) / pageable.pageSize,
                    currentPage = pageable.pageNumber,
                    isLast = pageable.pageNumber >= (totalElements / pageable.pageSize),
                    pageSize = pageable.pageSize,
                    isFirst = pageable.pageNumber == 0,
                    content = content
                )
            }
        } catch (err: Exception) {
            return PaginatedResult(
                totalElements = 0,
                totalPages = 0,
                currentPage = pageable.pageNumber,
                isLast = true,
                pageSize = pageable.pageSize,
                isFirst = true,
                content = listOf()
            )
        }
    }

    override fun savePost(post: PostCore): PostCore? {
        val id = java.util.UUID.randomUUID()
        val output = this.indexDocument<PostsDbModel>(PostsDbModel(
            id = id,
            date = post.date,
            content = post.content,
            title = post.title,
            author = ClientDbCore(
                id = post.author?.id,
                name = post.author?.name?:"",
                role = post.author?.role,
                lastName = post.author?.lastName?:""
            )
        ))
        val status = output?.statusLine?.statusCode
        if(status != HttpStatus.CREATED.value()) return null
        return post.copy(id = id)
    }
}