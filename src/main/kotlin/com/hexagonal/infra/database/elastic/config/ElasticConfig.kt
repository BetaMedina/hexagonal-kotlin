package com.hexagonal.infra.database.elastic.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hexagonal.infra.database.elastic.dto.SearchCriteriaDbDTO
import com.hexagonal.infra.database.elastic.impl.PostRepositoryImpl
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value


open class ElasticConfig(@Value("\${spring.elasticsearch.rest.uris}") private val url: String, private val indexName: String) {
    private val logger: Logger = LoggerFactory.getLogger(ElasticConfig::class.java)

    fun <T> indexDocument(payload: T): CloseableHttpResponse? {
        try{
            val objectMapper = jacksonObjectMapper()
            val httpClient = HttpClients.createDefault()
            val httpPost = HttpPost("${url}/${indexName}/_doc")

            httpPost.setHeader(org.apache.http.HttpHeaders.CONTENT_TYPE, "application/json")
            httpPost.setHeader(org.apache.http.HttpHeaders.ACCEPT, "application/json")

            val jsonPayload = objectMapper.writeValueAsString(payload)
            httpPost.entity = StringEntity(jsonPayload)

            val output = httpClient.execute(httpPost)
            output.close()
            return output
        }catch(err:Exception){
            logger.error("Error on send to Elasticsearch: ${err.message}", err)
            throw err
        }
    }

    private fun buildSearchRequest(criteria: SearchCriteriaDbDTO): Map<String, Any> {
        val searchRequest = mutableMapOf<String, Any>(
            "size" to criteria.size,
            "from" to criteria.from
        )

        criteria.query?.let { query ->
            searchRequest["query"] = mapOf("match" to mapOf("field" to query))
        }

        criteria.sort?.let { sortFields ->
            searchRequest["sort"] = sortFields.map { mapOf(it.field to mapOf("order" to it.order.name.lowercase())) }
        }

        criteria.filters?.let { filters ->
            searchRequest["filter"] = filters
        }

        return searchRequest
    }

    fun getDocument(payload: SearchCriteriaDbDTO): CloseableHttpResponse? {
        try{
            val objectMapper = jacksonObjectMapper()
            val httpClient = HttpClients.createDefault()
            val httpPost = HttpPost("${url}/${indexName}/_search")

            httpPost.setHeader(org.apache.http.HttpHeaders.CONTENT_TYPE, "application/json")
            httpPost.setHeader(org.apache.http.HttpHeaders.ACCEPT, "application/json")

            val jsonPayload = objectMapper.writeValueAsString(buildSearchRequest(payload))
            httpPost.entity = StringEntity(jsonPayload)
            val output = httpClient.execute(httpPost)

            return output
        }catch(err: Exception){
            logger.error("Error on send to Elasticsearch: ${err.message}", err)
            throw err
        }
    }
}