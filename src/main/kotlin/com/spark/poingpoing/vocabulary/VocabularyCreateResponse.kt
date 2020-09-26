package com.spark.poingpoing.vocabulary

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class VocabularyCreateResponse(
        @ApiModelProperty(value = "단어 Id", example = "1")
        val id: Long
)