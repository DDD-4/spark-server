package com.spark.poingpoing.vocabulary.every

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.multipart.MultipartFile


@ApiModel
data class EveryVocabularyRequest(
        @ApiModelProperty(value = "내려받을 나의 폴더 Id", example = "3")
        val myFolderId: Long,

        @ApiModelProperty(value = "내려받을 모두의 단어 Id List")
        val vocabularyIds: List<Long>?
)