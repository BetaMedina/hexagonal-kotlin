package com.hexagonal.core.port.`in`

import com.hexagonal.core.dtos.PaginatedResult
import com.hexagonal.core.model.PostCore
import org.springframework.data.domain.Page

interface PostPort {
    fun getPosts(page:Int,size:Int):PaginatedResult<PostCore>
    fun createPost(body:PostCore):PostCore
}