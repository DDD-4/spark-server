package com.spark.poingpoing.user

import io.swagger.annotations.ApiModelProperty

data class LoginRequest(
        @ApiModelProperty(value = "유저 인증키", required = true)
        val credential: String
)
