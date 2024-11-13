package com.hexagonal.infra.database.elastic.model

import com.hexagonal.core.enums.ClientRolesEnum
import java.util.*

data class ClientDbCore(
    val id: Int? = null,
    val name: String,
    val lastName: String,
    val role: ClientRolesEnum?= ClientRolesEnum.CLIENT
)

data class PostsDbModel(
    val id: UUID,
    val title: String,
    val content: String,
    val author: ClientDbCore?,
    val date: Date
)
