package com.hexagonal.core.model

import java.util.*

data class PostCore (
    val id: Int?,
    val title: String,
    val content: String,
    val author: ClientCore?,
    val date: Date
)