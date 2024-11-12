package com.hexagonal.entrypoint.http.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class LoginRequestDto (
    @field:NotBlank(message = "Cpf can't be blank")
    @field:NotEmpty(message= "Cpf can't be empty")
    val cpf: String,

    @field:NotBlank(message = "Password can't be blank")
    @field:NotEmpty(message= "Password can't be empty")
    val password:String
)
