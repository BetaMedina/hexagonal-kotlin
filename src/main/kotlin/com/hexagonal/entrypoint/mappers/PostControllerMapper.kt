package com.hexagonal.entrypoint.mappers

import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.core.model.AuthorPostCore
import com.hexagonal.core.model.ClientCore
import com.hexagonal.core.model.PostCore
import com.hexagonal.entrypoint.http.dto.request.PostSaveRequest
import com.hexagonal.entrypoint.http.dto.response.AuthorDto
import com.hexagonal.entrypoint.http.dto.response.PostResponseDto
import java.util.Date

fun PostCore.toResponse(): PostResponseDto {
    return PostResponseDto(
        id = this.id,
        author = AuthorDto(
            id = this.author?.id,
            name = this.author?.name.toString(),
            lastName = this.author?.lastName?: "",
            role = this.author?.role
        ),
        date = this.date,
        title = this.title,
        content = this.content

    )
}


fun PostSaveRequest.toModel(): PostCore{
    return PostCore(
        author = AuthorPostCore(
            id = this.authorId,
            name = "",
            lastName = "",
        ),
        title = this.title,
        content = this.content,
        date = Date(),
        id = null
    )
}
