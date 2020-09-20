package com.spark.poingpoing.code

import io.swagger.annotations.ApiModelProperty

data class EnumValue(
        @ApiModelProperty(value = "enum Key", example = "LATEST")
        val key: String,

        @ApiModelProperty(value = "Enum Value", example = "POPULAR")
        val value: String
)