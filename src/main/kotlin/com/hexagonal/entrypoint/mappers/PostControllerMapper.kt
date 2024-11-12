package com.hexagonal.entrypoint.mappers

import com.hexagonal.core.enums.ClientStatusEnum
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
            cpf = this.author?.cpf,
            name = this.author?.name.toString(),
            status = this.author?.status,
            lastName = this.author?.lastName?: ""
        ),
        date = this.date,
        title = this.title,
        content = this.content

    )
}


fun PostSaveRequest.toModel(): PostCore{
    return PostCore(
        author = ClientCore(
            id = this.authorId,
            cpf = "",
            name = "",
            status = ClientStatusEnum.ACTIVE,
            lastName = "",
            password = ""
        ),
        title = this.title,
        content = this.content,
        date = Date(),
        id = null
    )
}
