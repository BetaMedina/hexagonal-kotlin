package com.hexagonal.entrypoint.http.dto.request

import com.hexagonal.core.enums.ClientStatusEnum
import jakarta.annotation.Nullable
import jakarta.persistence.Enumerated

data class ClientUpdateRequest (
    val firstName: String?,
    val lastName: String?,

    @field:Enumerated()
    @field:Nullable
    val status: ClientStatusEnum?
)