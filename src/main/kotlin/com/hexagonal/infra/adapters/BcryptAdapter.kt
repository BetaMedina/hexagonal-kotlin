package com.hexagonal.infra.adapters

import com.hexagonal.core.port.out.CryptAdapterPort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BcryptAdapter:CryptAdapterPort {
    override fun anonymize(password: String):String{
        return BCryptPasswordEncoder().encode(password)
    }

    override fun compare(dbPassword: String, password: String): Boolean {
        return BCryptPasswordEncoder().matches(password,dbPassword)
    }
}