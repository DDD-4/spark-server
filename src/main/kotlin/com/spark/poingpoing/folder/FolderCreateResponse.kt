package com.spark.poingpoing.folder

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class FolderCreateResponse(
        @ApiModelProperty(value = "폴더 Id", example = "1")
        val id: Long
)