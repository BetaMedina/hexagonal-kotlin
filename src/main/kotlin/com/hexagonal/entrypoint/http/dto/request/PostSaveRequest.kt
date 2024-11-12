package com.hexagonal.entrypoint.http.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostSaveRequest (
    @field:Size(min = 3, message = "The size need to be greater than 3")
    @field:NotBlank(message = "title can't be empty")
    val title: String,

    @field:Size(min = 3, message = "The size need to be greater than 3")
    @field:NotBlank(message = "Content can't be empty")
    val content: String,

    val authorId: Int,
)