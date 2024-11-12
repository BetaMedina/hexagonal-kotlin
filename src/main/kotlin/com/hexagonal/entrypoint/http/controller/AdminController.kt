package com.hexagonal.entrypoint.http.controller

import com.hexagonal.core.usecase.AdminUseCase
import com.hexagonal.entrypoint.http.dto.request.AdminUpdateRolesRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController(private val adminUseCase: AdminUseCase) {
    @PatchMapping("/client/{id}/roles")
    fun updateRoles(@Valid @RequestBody body:AdminUpdateRolesRequest,@PathVariable id: Int):ResponseEntity<Void>{
        this.adminUseCase.changeRoles(enumValueOf(body.role),id)
        return ResponseEntity.noContent().build<Void>()
    }

}