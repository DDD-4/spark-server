package com.spark.poingpoing.user.auth

import com.spark.poingpoing.user.User
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtTokenProvider(
        @Value("\${jwt.secret}") private var SECRET_KEY: String) {

    companion object {
        private const val EXPIRATION_MS: Long = 1000 * 60 * 60 * 24 * 15
    }

    private val log = LoggerFactory.getLogger(javaClass)

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
        } catch (e: SignatureException) {
            log.error("$token::Invalid JWT signature", e)
            return false
        } catch (e: MalformedJwtException) {
            log.error("$token::Invalid JWT token", e)
            return false
        } catch (e: ExpiredJwtException) {
            log.error("$token::Expired JWT token", e)
            return false
        } catch (e: UnsupportedJwtException) {
            log.error("$token::Unsupported JWT token", e)
            return false
        } catch (e: IllegalArgumentException) {
            log.error("$token::JWT claims String is Empty JWT token", e)
            return false
        }
        return true
    }

    fun getTokenUserEmail(token: String): String {
        val claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .body

        return claims.subject
    }

    fun createToken(user: User): String {
        val now = LocalDateTime.now()
        val expiredAt = now.plus(EXPIRATION_MS, ChronoUnit.MILLIS)

        return Jwts.builder()
                .setHeader(createHeader(now))
                .setClaims(createClaims(user))
                .setSubject(user.email)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setExpiration(Date.from(expiredAt.atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact()
    }

    private fun createClaims(user: User): Map<String, Any> {
        val claims = mutableMapOf<String, Any>()
        claims["name"] = user.name
        return claims
    }

    private fun createHeader(now: LocalDateTime): Map<String, Any> {
        val header = mutableMapOf<String, Any>()
        header["type"] = "JWT"
        header["alg"] = "HS512"
        header["regDate"] = now
        return header
    }
}