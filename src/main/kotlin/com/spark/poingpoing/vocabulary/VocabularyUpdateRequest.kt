package com.spark.poingpoing.vocabulary

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.multipart.MultipartFile

@ApiModel
data class VocabularyUpdateRequest(
        @ApiModelProperty(value = "영어", example = "tomato")
        val english: String?,

        @ApiModelProperty(value = "한글", example = "토마토")
        val korean: String?,

        @ApiModelProperty(value = "폴더 Id", example = "3", required = true)
        val folderId: Long?,

        @JsonIgnore
        @ApiModelProperty(value = "사진")
        var photo: MultipartFile?,

        @JsonProperty("photo")
        var file: String?
)