package com.spark.poingpoing.folder

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class FolderCreateRequest(
        @ApiModelProperty(value = "폴더 이름", example = "과일", required = true)
        val name: String,

        @ApiModelProperty(value = "폴더 공유 여부", example = "true", required = true)
        val shareable: Boolean
)
