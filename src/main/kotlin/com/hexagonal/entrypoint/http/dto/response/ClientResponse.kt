package com.hexagonal.entrypoint.http.dto.response

import com.hexagonal.core.enums.ClientStatusEnum

data class ClientResponseDto (
    val id: Int?,
    val name: String,
    val lastName: String,
    val cpf: String?,
    val status: ClientStatusEnum
)
