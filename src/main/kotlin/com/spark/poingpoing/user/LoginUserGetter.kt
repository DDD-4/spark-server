package com.spark.poingpoing.user

import org.springframework.stereotype.Component

@Component
class LoginUserGetter(private val userRepository: UserRepository) {

    fun getLoginUser(): User {
        val defaultUserId = 1L

        return userRepository.findById(defaultUserId).get()
    }
}