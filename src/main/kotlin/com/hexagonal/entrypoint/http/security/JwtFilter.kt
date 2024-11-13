package com.hexagonal.entrypoint.http.security

import com.hexagonal.core.enums.ClientRolesEnum
import com.hexagonal.core.port.out.ClientRepositoryPort
import com.hexagonal.infra.adapters.JwtUtil
import com.hexagonal.infra.database.mysql.model.ClientDbModel
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userRepository: ClientRepositoryPort
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader("Authorization")

        var id: String? = null
        var jwt: String? = null

        if (authorizationHeader != null) {
            if(authorizationHeader.startsWith("Bearer ")){
                jwt = authorizationHeader.substring(7)
                id = jwtUtil.extractId(jwt)
            }
        }

        if (id != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = this.userRepository.findById(id.toInt())

            if (jwtUtil.validateToken(jwt!!, userDetails?.id.toString())) {
                val authToken = UsernamePasswordAuthenticationToken(id, null, listOf(SimpleGrantedAuthority("ROLE_${userDetails?.role.toString()}")))
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        chain.doFilter(request, response)
    }
}