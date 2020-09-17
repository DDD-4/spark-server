package com.spark.poingpoing.folder

import io.swagger.annotations.ApiModelProperty

data class FolderCreateResponse(
        @ApiModelProperty(value = "폴더 Id", example = "1")
        val id: Long
)