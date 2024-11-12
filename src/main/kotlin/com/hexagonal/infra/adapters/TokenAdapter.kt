package com.hexagonal.infra.adapters
import com.hexagonal.core.port.out.TokenAdapterPort
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date
import javax.crypto.spec.SecretKeySpec


@Component
class JwtUtil(
   @Value("\${token.secret}")
   private val SECRET_KEY: String
): TokenAdapterPort {

    override fun generateToken(username: String,claims:HashMap<String, Any>?): String {
        return createToken(claims?:HashMap(), username)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        val secretKeyBytes =  Base64.getEncoder().encodeToString(SECRET_KEY.toByteArray())
        return  Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
            .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
            .compact()
    }

    override fun validateToken(token: String, username: String): Boolean {
        val extractedUsername = extractUsername(token)
        return extractedUsername == username && !isTokenExpired(token)
    }

    override fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    override fun extractId(token: String): String {
        return extractClaim(token, { claims -> claims["id"] as String })
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val secretKeyBytes =  Base64.getEncoder().encodeToString(SECRET_KEY.toByteArray())
        val claims = Jwts.parser().setSigningKey(secretKeyBytes).parseClaimsJws(token).body
        return claimsResolver(claims)
    }
}