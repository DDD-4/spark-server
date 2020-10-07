package com.spark.poingpoing.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.multipart.MultipartFile

@ApiModel
data class UserUpdateRequest(
        @ApiModelProperty(value = "이름", example = "홍길동", required = true)
        val name: String?,

        @JsonIgnore
        @ApiModelProperty(value = "프로필 사진", required = true)
        var photo: MultipartFile?,

        @JsonProperty("photo")
        var file: String?
)