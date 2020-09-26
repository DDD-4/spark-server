package com.spark.poingpoing.vocabulary.every

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
open class EveryVocabularyRequest(
        @ApiModelProperty(value = "내려받을 나의 폴더 Id", example = "3")
        open val myFolderId: Long
)