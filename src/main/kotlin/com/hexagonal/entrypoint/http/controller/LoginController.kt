package com.hexagonal.entrypoint.http.controller

import com.hexagonal.core.port.`in`.LoginPort
import com.hexagonal.entrypoint.http.dto.request.LoginRequestDto
import com.hexagonal.entrypoint.http.dto.response.LoginResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class LoginController(private val loginUseCase: LoginPort) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody body: LoginRequestDto):ResponseEntity<LoginResponse>{
        val token = this.loginUseCase.createSession(body.cpf,body.password)
        return ResponseEntity.ok(LoginResponse(token = token))
    }
}