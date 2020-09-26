package com.spark.poingpoing.vocabulary.every

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class EveryVocabularyIdsRequest(
        @ApiModelProperty(value = "내려받을 모두의 단어 Id List")
        val vocabularyIds: List<Long>,

        @ApiModelProperty(value = "내려받을 나의 폴더 Id", example = "3")
        override val myFolderId: Long

) : EveryVocabularyRequest(myFolderId)