package com.spark.poingpoing.folder

import io.swagger.annotations.ApiModelProperty

data class UpdateDeleteFolderRequest(
        @ApiModelProperty(value = "폴더 Id 리스트", required = true)
        val folderIds: List<Long>
)
