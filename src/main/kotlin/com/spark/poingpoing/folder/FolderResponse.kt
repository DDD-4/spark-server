package com.spark.poingpoing.folder

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class FolderResponse(
        @ApiModelProperty(value = "폴더 Id", example = "1")
        val id: Long,

        @ApiModelProperty(value = "폴더 이름", example = "과일")
        val name: String,

        @ApiModelProperty(value = "폴더 공유 여부", example = "true")
        val shareable: Boolean,

        @ApiModelProperty(value = "기본 폴더 여부", example = "false")
        val default: Boolean,

        @ApiModelProperty(value = "대표 사진(없는 경우 보내지 않음)")
        val photoUrl: String?
)