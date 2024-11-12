package com.hexagonal.entrypoint.http

import com.hexagonal.core.port.`in`.ClientPort
import com.hexagonal.entrypoint.http.dto.request.ClientSaveRequest
import com.hexagonal.entrypoint.http.dto.request.ClientUpdateRequest
import com.hexagonal.entrypoint.http.dto.response.ClientResponseDto
import com.hexagonal.entrypoint.http.dto.response.ClientSaveResponseDto
import com.hexagonal.entrypoint.mappers.toClientResponse
import com.hexagonal.entrypoint.mappers.toModel
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/clients")
class ClientController(private val clientUseCase: ClientPort) {
    @GetMapping("/document/{doc}")
    fun getDataByCpf(@PathVariable("doc") cpf: String): ResponseEntity<ClientResponseDto> {
        val output = this.clientUseCase.getClientByCpf(cpf)
        return ResponseEntity.ok(output?.toClientResponse())
    }

    @PostMapping
    fun saveClient(@Valid @RequestBody client: ClientSaveRequest):ResponseEntity<ClientSaveResponseDto> {
        val createdClient = this.clientUseCase.saveClient(client.toModel())
        return ResponseEntity.ok(
            ClientSaveResponseDto(
            id = createdClient.id!!,
            firstName = createdClient.name,
            lastName = createdClient.lastName,
            cpf = createdClient.cpf
            )
        )
    }

    @GetMapping("{id}")
    fun getClientById(@PathVariable id: Int): ResponseEntity<ClientResponseDto>{
        val client = this.clientUseCase.getClientById(id)
        return ResponseEntity.ok(
            ClientResponseDto(
                id = client.id!!,
                name = client.name,
                lastName = client.lastName,
                cpf = client.cpf,
                status= client.status
            )
        )
    }

    @PatchMapping("/{id}")
    fun updateClient(@RequestBody clientData: ClientUpdateRequest,@PathVariable("id") id:Int):ResponseEntity<ClientSaveResponseDto>{
        val output = this.clientUseCase.updateClient(id,clientData.firstName,clientData.lastName,clientData.status)
        return ResponseEntity.ok(
            ClientSaveResponseDto(
                id = output.id!!,
                firstName = output.name,
                lastName = output.lastName,
                cpf = output.cpf
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Int): ResponseEntity<Void> {
        this.clientUseCase.deleteClient(id)
        return ResponseEntity.noContent().build<Void>()
    }
}
