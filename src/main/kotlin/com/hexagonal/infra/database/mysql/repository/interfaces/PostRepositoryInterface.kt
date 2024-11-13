package com.hexagonal.infra.database.mysql.repository.interfaces

//import com.hexagonal.infra.database.mysql.model.PostDbModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
//
//interface PostRepositoryInterface: JpaRepository<PostDbModel,Int> {
//    @Query("SELECT p FROM post p")
//    fun findPosts(pageable: PageRequest): Page<PostDbModel>?
//}