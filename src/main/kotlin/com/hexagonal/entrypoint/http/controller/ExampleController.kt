package com.hexagonal.entrypoint.http.controller

import com.hexagonal.core.model.ExampleCore
import com.hexagonal.infra.database.elastic.impl.ExampleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class ExampleController(private val exampleRepository: ExampleService) {
    @PostMapping
    fun test(@RequestBody body: ExampleCore):ResponseEntity<*>{
        this.exampleRepository.indexDocument(body)
        return ResponseEntity.ok("Ok")
    }
}