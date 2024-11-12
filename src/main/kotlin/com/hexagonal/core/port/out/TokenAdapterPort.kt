package com.hexagonal.core.port.out

interface TokenAdapterPort {
    fun validateToken(token: String, username: String): Boolean
    fun extractUsername(token: String): String
    fun extractId(token: String): String
    fun generateToken(username: String,claims:HashMap<String, Any>?): String
}