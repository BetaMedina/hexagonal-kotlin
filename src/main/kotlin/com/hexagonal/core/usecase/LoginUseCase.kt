package com.hexagonal.core.usecase

import com.hexagonal.core.enums.ErrorEnum
import com.hexagonal.core.exceptions.BadRequestException
import com.hexagonal.core.exceptions.NotFoundException
import com.hexagonal.core.port.`in`.LoginPort
import com.hexagonal.core.port.out.ClientRepositoryPort
import com.hexagonal.core.port.out.CryptAdapterPort
import com.hexagonal.core.port.out.TokenAdapterPort
import org.springframework.stereotype.Service
import java.util.HashMap

@Service
class LoginUseCase(
    private val clientRepositoryPort: ClientRepositoryPort,
    private val cryptAdapter: CryptAdapterPort,
    private val tokenAdapter: TokenAdapterPort
): LoginPort {
    override fun createSession(cpf: String, password: String): String {
        val client = this.clientRepositoryPort.findByCpf(cpf)?:throw NotFoundException(ErrorEnum.HX1001.message,ErrorEnum.HX1001.code)
        if(!cryptAdapter.compare(client.password,password)){
            throw BadRequestException(ErrorEnum.HX1002.message,ErrorEnum.HX1002.code)
        }
        val claims = HashMap<String, Any>()
        claims["username"] = client.name
        claims["id"] = client.id.toString()
        claims["role"] = "CUSTOMER"
        return this.tokenAdapter.generateToken(client.id.toString(), claims)
    }
}