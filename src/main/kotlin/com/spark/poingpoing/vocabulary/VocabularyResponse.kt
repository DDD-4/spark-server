package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.util.PageResponse
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class VocabularyResponse(
        @ApiModelProperty(value = "단어 Id", example = "1")
        val id: Long,

        @ApiModelProperty(value = "영어명", example = "apple")
        val english: String,

        @ApiModelProperty(value = "한글명", example = "사과")
        val korean: String,

        @ApiModelProperty(value = "이미지 Url", example = "")
        val photoUrl: String
)

@ApiModel
class VocabularyPageResponse(
        @ApiModelProperty(value = "리스트")
        val content: List<VocabularyResponse>,

        @ApiModelProperty(value = "다음 페이지 여부")
        val hasNext: Boolean,

        @ApiModelProperty(value = "전체 갯수")
        val totalCount: Long
) : PageResponse<VocabularyResponse>(content, hasNext, totalCount)