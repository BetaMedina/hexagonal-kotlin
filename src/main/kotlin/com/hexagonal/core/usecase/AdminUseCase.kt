package com.hexagonal.core.usecase

import com.hexagonal.core.enums.ClientRolesEnum
import com.hexagonal.core.enums.ErrorEnum
import com.hexagonal.core.exceptions.NotFoundException
import com.hexagonal.core.port.`in`.AdminPort
import com.hexagonal.core.port.out.ClientRepositoryPort
import org.springframework.stereotype.Service

@Service
class AdminUseCase(private val clientRepository: ClientRepositoryPort): AdminPort {
    override fun changeRoles(rolesEnum: ClientRolesEnum, id: Int) {
        val getClient = this.clientRepository.findById(id)?:throw NotFoundException(ErrorEnum.HX1001.message,ErrorEnum.HX1001.code)
        val updatedClient = getClient.copy(role = rolesEnum)
        this.clientRepository.save(updatedClient)
    }
}