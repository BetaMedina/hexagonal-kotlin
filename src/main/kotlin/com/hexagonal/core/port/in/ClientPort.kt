package com.hexagonal.core.port.`in`

import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.core.model.ClientCore

interface ClientPort {
    fun getClientByCpf(cpf: String): ClientCore?
    fun saveClient(client:ClientCore):ClientCore
    fun updateClient(id:Int, firstName: String?, lastName:String?, status: ClientStatusEnum?):ClientCore
    fun getClientById(id:Int):ClientCore
    fun deleteClient(id: Int)
}