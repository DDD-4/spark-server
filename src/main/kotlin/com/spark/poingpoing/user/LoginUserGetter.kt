package com.spark.poingpoing.user

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class LoginUserGetter(private val userRepository: UserRepository) {

    fun getLoginUser(httpServletRequest: HttpServletRequest): User {
        val defaultUserId = 1L

        return userRepository.findById(defaultUserId).get()
    }
}