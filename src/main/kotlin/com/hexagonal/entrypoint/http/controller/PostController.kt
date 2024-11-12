package com.hexagonal.entrypoint.http.controller

import com.hexagonal.core.dtos.PaginatedResult
import com.hexagonal.core.port.`in`.PostPort
import com.hexagonal.entrypoint.http.dto.request.PostSaveRequest
import com.hexagonal.entrypoint.http.dto.response.PostResponseDto
import com.hexagonal.entrypoint.mappers.toModel
import com.hexagonal.entrypoint.mappers.toResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController (private val postUseCase: PostPort){
    @GetMapping
    fun getPost(@RequestParam page: Int?,@RequestParam size: Int?):ResponseEntity<PaginatedResult<PostResponseDto>> {
        val output = this.postUseCase.getPosts(page?:0,size?:10)
        return ResponseEntity.ok(
            PaginatedResult(
                isLast = output.isLast,
                pageSize = output.pageSize,
                isFirst = output.isFirst,
                content = output.content?.map { it.toResponse() },
                totalElements = output.totalElements,
                totalPages = output.totalPages,
                currentPage = output.currentPage
            )

        )
    }

    @PostMapping
    fun createPost(@RequestBody @Valid payload:PostSaveRequest): ResponseEntity<PostResponseDto>{
        val output = this.postUseCase.createPost(payload.toModel())
        return ResponseEntity.ok(output.toResponse())
    }

}