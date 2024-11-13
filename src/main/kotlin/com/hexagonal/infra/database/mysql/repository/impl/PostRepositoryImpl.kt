package com.hexagonal.infra.database.mysql.repository.impl

import com.hexagonal.core.dtos.PaginatedResult
import com.hexagonal.core.model.PostCore
import com.hexagonal.core.port.out.PostRepositoryPort
import com.hexagonal.infra.database.mysql.model.ClientDbModel
//import com.hexagonal.infra.database.mysql.model.PostDbModel
//import com.hexagonal.infra.database.mysql.repository.interfaces.PostRepositoryInterface
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

//@Repository
//@Primary
//class PostRepositoryAdapter(
//    private val springData: PostRepositoryInterface
//):PostRepositoryPort {
//    override fun getAll(pageable: PageRequest): PaginatedResult<PostCore> {
//        val output = this.springData.findPosts(pageable)
//        return PaginatedResult(
//            content = output?.content?.map { it.toModel() },
//            totalPages = output?.totalPages,
//            currentPage = output?.number,
//            isLast = output?.isLast,
//            pageSize = output?.size,
//            isFirst =  output?.isFirst,
//            totalElements = output?.numberOfElements,
//        )
//    }
//
//    override fun savePost(post: PostCore): PostCore {
//       val output = this.springData.save(
//           PostDbModel(
//           content = post.content,
//           title = post.title,
//           id = null,
//           date = post.date,
//           author = ClientDbModel(
//               name = post.author?.name!!,
//               cpf = post.author.cpf,
//               status = post.author.status,
//               id = post.author.id!!,
//               lastName = post.author.lastName,
//           )
//       )
//       )
//        return output.toModel()
//    }
//}