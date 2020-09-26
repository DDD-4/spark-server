package com.spark.poingpoing.code

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class EnumValue(
        @ApiModelProperty(value = "enum Key", example = "LATEST")
        val key: String,

        @ApiModelProperty(value = "Enum Value", example = "최신순")
        val value: String
)