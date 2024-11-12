package com.hexagonal.core.port.out

interface CryptAdapterPort {
    fun anonymize(password: String):String
    fun compare(dbPassword:String,password:String):Boolean
}