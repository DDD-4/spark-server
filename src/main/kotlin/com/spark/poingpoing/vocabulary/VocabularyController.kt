package com.spark.poingpoing.vocabulary

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@Api(tags = ["Voca"])
@RestController
class VocabularyController {

    @ApiOperation("나의 단어 등록")
    @PostMapping("v1/vocabularies")
    fun createVocabulary(@RequestBody vocabularyRequest: VocabularyRequest) {

    }

    @ApiOperation("나의 단어 수정")
    @PatchMapping("v1/vocabularies/{vocabularyId}")
    fun modifyVocabulary(@PathVariable vocabularyId: Long,
                         @RequestBody vocabularyRequest: VocabularyRequest) {

    }

    @ApiOperation("나의 단어 삭제")
    @DeleteMapping("v1/vocabularies/{vocabularyId}")
    fun deleteVocabulary(@PathVariable vocabularyId: Long) {

    }

    @ApiOperation("모두의 단어장 조회")
    @GetMapping("v1/vocabularies/all")
    fun getAllVocabularies(): List<VocabularyResponse> {

        return listOf()
    }

    @ApiOperation("모두의 단어장 가져오기")
    @PostMapping("v1/vocabularies/{vocabularyId}")
    fun copyVocabulary(@PathVariable vocabularyId: Long) {

    }

    @ApiOperation("모두의 단어장 폴더별 가져오기")
    @PostMapping("v1/folders/{folderId}")
    fun copyVocabularies(@PathVariable folderId: Long) {

    }
}