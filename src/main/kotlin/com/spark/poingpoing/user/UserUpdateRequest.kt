package com.spark.poingpoing.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.multipart.MultipartFile

@ApiModel
data class UserUpdateRequest(
        @ApiModelProperty(value = "이름", example = "홍길동", required = true)
        val name: String?,

        @ApiModelProperty(value = "프로필 사진")
        var photo: MultipartFile?
)