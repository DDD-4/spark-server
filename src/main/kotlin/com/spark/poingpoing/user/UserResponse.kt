package com.spark.poingpoing.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class UserResponse(
        @ApiModelProperty(value = "유저 Id", example = "1")
        val id: Long,

        @ApiModelProperty(value = "이름", example = "홍길동")
        val name: String,

        @ApiModelProperty(value = "이미지 Url", example = "")
        val photoUrl: String
)