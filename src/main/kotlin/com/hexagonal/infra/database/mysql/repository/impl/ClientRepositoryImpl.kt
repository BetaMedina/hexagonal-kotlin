package com.hexagonal.infra.database.mysql.repository.impl

import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.core.model.ClientCore
import com.hexagonal.core.port.out.ClientRepositoryPort
import com.hexagonal.infra.database.mysql.model.ClientDbModel
import com.hexagonal.infra.database.mysql.repository.interfaces.ClientRepositoryInterface
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Primary
class UserRepositoryAdapter(
    private val springData: ClientRepositoryInterface
): ClientRepositoryPort {
    override fun findByCpf(cpf: String): ClientCore? {
        val output =  this.springData.findByCpf(cpf)?:return null
        return output.toModel()
    }

    override fun save(client:ClientCore):ClientCore{
        val output = this.springData.save(
            ClientDbModel(
            name = client.name,
            lastName = client.lastName,
            cpf = client.cpf,
            id = client.id,
            password = client.password,
            role = client.role

        )
        )
        return output.toModel()
    }

    override fun findById(id: Int): ClientCore? {
        val output: Optional<ClientDbModel> =  this.springData.findById(id)
        if (output.isPresent) {
           return output.get().toModel()
        }
        return null
    }

    override fun findByIdAndStatus(id: Int, status: ClientStatusEnum): ClientCore? {
        val output = this.springData.findByIdAndStatus(id,status)?: return null
        return output.toModel()
    }

    override fun delete(id: Int, deleted: ClientStatusEnum) {
       this.springData.delete(id,deleted)
    }
}