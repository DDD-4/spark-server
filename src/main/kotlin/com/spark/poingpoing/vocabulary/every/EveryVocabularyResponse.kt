package com.spark.poingpoing.vocabulary.every

import com.spark.poingpoing.util.PageResponse
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class EveryVocabularyResponse(
        @ApiModelProperty(value = "폴더 Id", example = "11")
        val folderId: Long,

        @ApiModelProperty(value = "폴더 이름", example = "내가 좋아하는 달달한 간식")
        val folderName: String,

        @ApiModelProperty(value = "폴더 주인", example = "홍길동")
        val userName: String,

        @ApiModelProperty(value = "대표 사진", example = "apple")
        var photoUrl: String,

        @ApiModelProperty(value = "이미지 갯수", example = "20")
        val count: Long
)

@ApiModel
class EveryVocabularyPageResponse(
        @ApiModelProperty(value = "리스트")
        val content: List<EveryVocabularyResponse>,

        @ApiModelProperty(value = "다음 페이지 여부")
        val hasNext: Boolean,

        @ApiModelProperty(value = "전체 갯수")
        val totalCount: Long
) : PageResponse<EveryVocabularyResponse>(content, hasNext, totalCount)