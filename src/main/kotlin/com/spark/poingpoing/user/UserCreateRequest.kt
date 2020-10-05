package com.spark.poingpoing.user

import io.swagger.annotations.ApiModelProperty

data class UserCreateRequest(
        @ApiModelProperty(value = "이름", example = "홍길동", required = true)
        val name: String,

        @ApiModelProperty(value = "유저 인증키", required = true)
        val credential: String
)


