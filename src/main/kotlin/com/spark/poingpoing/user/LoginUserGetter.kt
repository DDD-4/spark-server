package com.spark.poingpoing.user

import com.spark.poingpoing.exception.ForbiddenException
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class LoginUserGetter(private val userRepository: UserRepository) {

    fun getLoginUser(httpServletRequest: HttpServletRequest): User {
        val defaultUserId = 1L

        val user =  userRepository.findById(defaultUserId).get()
        if(!user.active) {
           throw ForbiddenException("사용자를 찾을 수 없습니다.")
        }

        return user
    }
}