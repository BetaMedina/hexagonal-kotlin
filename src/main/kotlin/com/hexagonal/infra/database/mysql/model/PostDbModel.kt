package com.hexagonal.infra.database.mysql.model

import com.hexagonal.core.model.ClientCore
import com.hexagonal.core.model.PostCore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.util.Date
//
//@Entity(name = "post")
//data class PostDbModel (
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Int?= null,
//
//    @Column(length = 200, nullable = false)
//    val title: String,
//
//    @Column(columnDefinition = "TEXT")
//    val content: String,
//
//    @CreatedDate
//    val date: Date,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    val author: ClientDbModel
//){
//    fun toModel(): PostCore {
//        return PostCore(
//            id = this.id,
//            title = this.title,
//            content = this.content,
//            date = this.date,
//            author = ClientCore(
//                id = this.author.id,
//                cpf = this.author.cpf,
//                status = this.author.status!!,
//                name = this.author.name,
//                lastName = this.author.lastName,
//                password = ""
//            )
//        )
//    }
//}
