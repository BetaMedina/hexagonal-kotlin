package com.hexagonal.infra.database.mysql.model

import ch.qos.logback.core.net.server.Client
import com.hexagonal.core.enums.ClientRolesEnum
import com.hexagonal.core.enums.ClientStatusEnum
import com.hexagonal.core.model.ClientCore
import jakarta.persistence.*

@Entity(name = "customer")
data class ClientDbModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?= null,

    @Column(length = 100, nullable = false)
    val name: String,

    @Column(length = 100, nullable = false)
    val lastName: String,

    @Column(length = 11, nullable = false, unique = true)
    val cpf: String,

    @Column(length = 11, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    val status: ClientStatusEnum?= ClientStatusEnum.ACTIVE,

    @Column(nullable = false)
    val password: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: ClientRolesEnum? = ClientRolesEnum.CLIENT
){
    fun toModel(): ClientCore {
        return ClientCore(
            id = this.id,
            name = this.name,
            lastName = this.lastName,
            cpf = this.cpf,
            status = this.status?: ClientStatusEnum.ACTIVE,
            password = this.password?:"",
            role = this.role
        )
    }
}
