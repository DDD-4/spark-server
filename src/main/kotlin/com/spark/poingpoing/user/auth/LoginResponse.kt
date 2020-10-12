package com.spark.poingpoing.user.auth

import com.spark.poingpoing.user.UserResponse
import io.swagger.annotations.ApiModelProperty

data class LoginResponse(
        @ApiModelProperty(value = "토큰", required = true)
        val token: String,
        @ApiModelProperty(value = "사용자 정보", example = "홍길동", required = true)
        val userResponse: UserResponse
)