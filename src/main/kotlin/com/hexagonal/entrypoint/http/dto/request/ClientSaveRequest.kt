package com.hexagonal.entrypoint.http.dto.request

import com.hexagonal.core.enums.ClientStatusEnum
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ClientSaveRequest (
    @field:Size(min = 3, message = "The size need to be greater than 3")
    @field:NotBlank(message = "First name can't be empty")
    val firstName: String?="",

    @field:Size(min = 3, message = "The size need to be greater than 3")
    @field:NotBlank(message = "First name can't be empty")
    val lastName: String?= "",

    @field:Size(min=11, max = 11, message = "Size must to be 11 characters")
    val cpf: String?= "",

    @field:Size(min=8, max = 11, message = "Password must to be greater than 8 characters")
    val password: String?= "",

    @field:Enumerated(EnumType.STRING)
    val status: ClientStatusEnum?=ClientStatusEnum.ACTIVE
)