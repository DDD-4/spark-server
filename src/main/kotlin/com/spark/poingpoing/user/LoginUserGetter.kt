package com.spark.poingpoing.user

import com.spark.poingpoing.exception.ForbiddenException
import com.spark.poingpoing.user.auth.JwtTokenProvider
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class LoginUserGetter(private val userRepository: UserRepository,
                      private val jwtTokenProvider: JwtTokenProvider) {

    fun getLoginUser(httpServletRequest: HttpServletRequest): User {
        val tokenHeader = httpServletRequest.getHeader("Authorization")
        val token = tokenHeader.substring(7)
        val user = userRepository.findFirstByEmailAndActiveIsTrue(jwtTokenProvider.getTokenUserEmail(token))
                .orElseThrow { ForbiddenException("사용자를 찾을 수 없습니다") }
        if (!user.active) {
            throw ForbiddenException("사용자를 찾을 수 없습니다.")
        }

        return user
    }
}