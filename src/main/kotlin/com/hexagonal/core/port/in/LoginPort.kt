package com.hexagonal.core.port.`in`

interface LoginPort {
    fun createSession(cpf:String, password:String):String
}