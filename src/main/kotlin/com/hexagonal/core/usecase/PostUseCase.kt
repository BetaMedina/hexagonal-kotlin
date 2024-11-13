package com.hexagonal.core.usecase

import com.hexagonal.core.dtos.PaginatedResult
import com.hexagonal.core.enums.ErrorEnum
import com.hexagonal.core.exceptions.NotFoundException
import com.hexagonal.core.model.AuthorPostCore
import com.hexagonal.core.model.PostCore
import com.hexagonal.core.port.`in`.PostPort
import com.hexagonal.core.port.out.ClientRepositoryPort
import com.hexagonal.core.port.out.PostRepositoryPort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.BadRequest

//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
@Service
class PostUseCase(
    private val postRepository:PostRepositoryPort,
    private val clientRepository: ClientRepositoryPort
): PostPort  {
    override fun getPosts(page: Int, size: Int): PaginatedResult<PostCore> {
        val pagination = PageRequest.of(page,size,Sort.by("date").descending())
        val output = this.postRepository.getAll(pagination)
        return output
    }

    override fun createPost(body: PostCore): PostCore? {
        val client = clientRepository.findById(body.author?.id!!)?: throw NotFoundException(ErrorEnum.HX1001.code,ErrorEnum.HX1001.message)
        val authorPost = AuthorPostCore(
            lastName = client.lastName,
            id = client.id,
            name = client.name,
            role = client.role
        )
        val updatedPost = body.copy(author = authorPost)
        return this.postRepository.savePost(updatedPost)
    }
}