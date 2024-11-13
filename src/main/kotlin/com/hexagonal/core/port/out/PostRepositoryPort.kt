package com.hexagonal.core.port.out

import com.hexagonal.core.dtos.PaginatedResult
import com.hexagonal.core.model.PostCore
import org.springframework.data.domain.PageRequest

interface PostRepositoryPort {
    fun getAll(pageable: PageRequest):PaginatedResult<PostCore>
    fun savePost(post: PostCore): PostCore?
}