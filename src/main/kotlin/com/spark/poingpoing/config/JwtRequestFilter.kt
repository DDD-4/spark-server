package com.spark.poingpoing.config

import com.spark.poingpoing.exception.ForbiddenException
import com.spark.poingpoing.user.UserRepository
import com.spark.poingpoing.user.auth.JwtTokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtRequestFilter(private val jwtTokenProvider: JwtTokenProvider,
                       private val userRepository: UserRepository) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val tokenHeader = request.getHeader("Authorization")
        if (tokenHeader.isNullOrEmpty()) {
            throw ForbiddenException("Authorization error")
        }
        if (!tokenHeader.startsWith("Bearer ")) {
            throw ForbiddenException("Authorization type error")
        }

        val token = tokenHeader.substring(7)
        if (token.isEmpty()) {
            throw ForbiddenException("JWT Token Error")
        }
        if (SecurityContextHolder.getContext().authentication == null && jwtTokenProvider.validateToken(token)) {
            val user = userRepository.findFirstByEmail(jwtTokenProvider.getTokenUserEmail(token))
                    .orElseThrow { ForbiddenException("사용자를 찾을 수 없습니다") }
            val userDetails: UserDetails = User(user.email, user.credential, mutableListOf())
            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities)
            usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        }

        filterChain.doFilter(request, response)
    }
}