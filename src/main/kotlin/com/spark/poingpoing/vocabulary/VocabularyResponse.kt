package com.spark.poingpoing.vocabulary

import io.swagger.annotations.ApiModelProperty

data class VocabularyResponse(
        @ApiModelProperty(value = "단어 Id", example = "1")
        val id: Long,

        @ApiModelProperty(value = "영어명", example = "apple")
        val english: String,

        @ApiModelProperty(value = "한글명", example = "사과")
        val korean: String,

        @ApiModelProperty(value = "이미지", example = "")
        val photo: String
)
