package com.spark.poingpoing.vocabulary

import io.swagger.annotations.ApiModelProperty

data class VocabularyCreateResponse(
        @ApiModelProperty(value = "단어 Id", example = "1")
        val id: Long
)