package com.hexagonal.infra.database.repository.interfaces

import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.infra.database.model.ClientDbModel
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ClientRepositoryInterface: CrudRepository<ClientDbModel,Int>{
    fun findByCpf(cpf: String): ClientDbModel?
    fun findByIdAndStatus(id:Int,status: ClientStatusEnum): ClientDbModel?

    @Modifying
    @Transactional
    @Query("UPDATE customer u SET u.status = :status where u.id = :id")
    fun delete(id: Int,status:ClientStatusEnum)
}