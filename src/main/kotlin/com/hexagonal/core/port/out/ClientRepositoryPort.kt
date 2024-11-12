package com.hexagonal.core.port.out

import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.core.model.ClientCore

interface ClientRepositoryPort {
    fun findByCpf(cpf:String): ClientCore?
    fun save(client:ClientCore): ClientCore
    fun findById(id:Int): ClientCore?
    fun findByIdAndStatus(id:Int, status:ClientStatusEnum): ClientCore?
    fun delete(id: Int, deleted: ClientStatusEnum)
}