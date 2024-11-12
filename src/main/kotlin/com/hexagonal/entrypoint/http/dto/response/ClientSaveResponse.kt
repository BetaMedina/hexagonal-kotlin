package com.hexagonal.entrypoint.http.dto.response

data class ClientSaveResponseDto (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val cpf: String
)