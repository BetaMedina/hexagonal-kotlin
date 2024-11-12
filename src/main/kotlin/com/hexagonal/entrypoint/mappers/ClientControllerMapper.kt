package com.hexagonal.entrypoint.mappers

import com.hexagonal.core.model.ClientCore
import com.hexagonal.entrypoint.http.dto.response.ClientResponseDto
import com.hexagonal.entrypoint.http.dto.request.ClientSaveRequest
import com.hexagonal.entrypoint.http.dto.request.ClientUpdateRequest

fun ClientCore.toClientResponse(): ClientResponseDto {
    return ClientResponseDto(
        id = this.id,
        name = this.name,
        lastName = this.lastName,
        cpf = this.cpf,
        status = this.status,
    )
}

fun ClientSaveRequest.toModel(): ClientCore{
    return ClientCore(
        name = this.firstName!!,
        password = this.password!!,
        lastName = this.lastName!!,
        cpf = this.cpf!!,
        status = this.status!!
    )
}
