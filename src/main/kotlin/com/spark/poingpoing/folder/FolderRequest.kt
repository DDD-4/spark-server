package com.spark.poingpoing.folder

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class FolderRequest(
        @ApiModelProperty(value = "폴더 이름", example = "과일")
        val name: String? = null,

        @ApiModelProperty(value = "폴더 공유 여부", example = "true")
        val shareable: Boolean? = null
)
