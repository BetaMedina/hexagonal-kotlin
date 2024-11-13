package com.hexagonal.entrypoint.http.dto.response

import com.hexagonal.core.enums.ClientRolesEnum
import com.hexagonal.core.enums.ClientStatusEnum
import java.util.*

data class AuthorDto(
    val id: Int?,
    val name: String,
    val lastName: String,
    val role: ClientRolesEnum?
)

data class PostResponseDto(
    val id: UUID?,
    val title: String,
    val content: String,
    val author: AuthorDto,
    val date: Date
)
