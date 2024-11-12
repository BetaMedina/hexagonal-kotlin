package com.hexagonal.entrypoint.http.dto.response

import com.hexagonal.core.enums.ClientStatusEnum
import java.util.*

data class AuthorDto(
    val id: Int?,
    val name: String,
    val lastName: String,
    val cpf: String?,
    val status: ClientStatusEnum?
)

data class PostResponseDto(
    val id: Int?,
    val title: String,
    val content: String,
    val author: AuthorDto,
    val date: Date
)