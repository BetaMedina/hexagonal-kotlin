package com.hexagonal.infra.database.elastic.impl

import com.hexagonal.core.model.ExampleCore
import com.hexagonal.core.port.out.ExampleRepositoryPort
import com.hexagonal.infra.database.elastic.config.ElasticConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component


@Component
class ExampleService(@Value("\${spring.elasticsearch.rest.uris}") private val url:String): ElasticConfig(url, "test-index"),ExampleRepositoryPort {
    private val logger: Logger = LoggerFactory.getLogger(ExampleService::class.java)
    override fun create(document: ExampleCore): ExampleCore? {
        try{
            val output = this.indexDocument<ExampleCore>(document)
            val status = output?.statusLine?.statusCode
            if(status != HttpStatus.CREATED.value()) return null
            return document
        }catch(err:Exception){
            logger.error("Error on send to elasticsearch: ${err.message}",err.stackTrace)
            return null
        }
    }
}