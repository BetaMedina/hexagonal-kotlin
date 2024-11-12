package com.hexagonal.core.usecase

import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.core.enums.ErrorEnum
import com.hexagonal.core.exceptions.NotFoundException
import com.hexagonal.core.model.ClientCore
import com.hexagonal.core.port.`in`.ClientPort
import com.hexagonal.core.port.out.ClientRepositoryPort
import com.hexagonal.core.port.out.CryptAdapterPort
import org.springframework.stereotype.Service

@Service
class ClientUseCase(
    private val clientRepository: ClientRepositoryPort,
    private val cryptAdapter: CryptAdapterPort
): ClientPort  {
    override fun getClientByCpf(cpf: String): ClientCore? {
        return  clientRepository.findByCpf(cpf)?: throw NotFoundException(ErrorEnum.HX1001.message,ErrorEnum.HX1001.code)
    }

    override fun saveClient(client: ClientCore): ClientCore {
        val anonymizedPassword = cryptAdapter.anonymize(client.password)
        val clientToSave = client.copy(password = anonymizedPassword)
        return this.clientRepository.save(clientToSave)
    }

    override fun updateClient(id:Int, firstName: String?, lastName: String?, status: ClientStatusEnum?): ClientCore {
        val client = this.clientRepository.findById(id)?: throw NotFoundException(ErrorEnum.HX1001.message,ErrorEnum.HX1001.code)
        val newClient = ClientCore(
            name = firstName?: client.name,
            lastName = lastName ?: client.lastName,
            id = id,
            cpf = client.cpf,
            status = status ?: client.status,
            password = client.password,
            role = client.role
        )
        return this.clientRepository.save(newClient)
    }

    override fun getClientById(id: Int): ClientCore {
        return this.clientRepository.findByIdAndStatus(id,ClientStatusEnum.ACTIVE)?:throw NotFoundException(ErrorEnum.HX1001.message,ErrorEnum.HX1001.code)
    }

    override fun deleteClient(id: Int) {
        this.clientRepository.delete(id,ClientStatusEnum.DELETED)
    }

}