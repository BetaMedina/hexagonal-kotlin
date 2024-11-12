package com.hexagonal.core.model

import com.hexagonal.core.enums.ClientRolesEnum
import com.hexagonal.core.enums.ClientStatusEnum

data class ClientCore(
    val id: Int? = null,
    val name: String,
    val lastName: String,
    val cpf: String,
    val status: ClientStatusEnum,
    val password: String,
    val role: ClientRolesEnum?= ClientRolesEnum.CLIENT
)