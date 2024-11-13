package com.hexagonal.core.model

import com.hexagonal.core.enums.ClientRolesEnum
import com.hexagonal.core.enums.ClientStatusEnum
import java.util.*


data class AuthorPostCore(
    val id: Int? = null,
    val name: String,
    val lastName: String,
    val cpf:String? = null,
    val status:ClientStatusEnum?=ClientStatusEnum.ACTIVE,
    val password: String?=null,
    val role: ClientRolesEnum?= ClientRolesEnum.CLIENT
)

data class PostCore(
    val id: UUID?,
    val title: String,
    val content: String,
    val author: AuthorPostCore?,
    val date: Date
)