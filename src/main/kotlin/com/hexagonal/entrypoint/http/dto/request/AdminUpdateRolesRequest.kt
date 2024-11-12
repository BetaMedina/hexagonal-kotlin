package com.hexagonal.entrypoint.http.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class AdminUpdateRolesRequest(
    @field:NotNull(message = "Role is required")
    @field:Pattern(regexp = "^(ADMIN|CLIENT)$", message = "Role must be equal: 'ADMIN', 'CLIENT' ")
    val role: String = ""
)
