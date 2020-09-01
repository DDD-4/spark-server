package com.spark.poingpoing.vocabulary

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.io.File
import java.net.InetAddress

@Api(tags = ["Voca"])
@RestController
class VocabularyController {

    @ApiOperation("나의 단어 등록")
    @PostMapping("v1/vocabularies")
    fun createVocabulary(vocabularyRequest: VocabularyRequest) {

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
        val ip = "http://15.165.176.148"

        val dummyVocabulary1 = VocabularyResponse(
                id = 1,
                english = "apple",
                korean = "사과",
                photoUrl = "$ip:8080/v1/photos/apple.jpeg"
        )

        val dummyVocabulary2 = VocabularyResponse(
                id = 2,
                english = "desk",
                korean = "책상",
                photoUrl = "$ip:8080/v1/photos/desk.jpeg"
        )

        return listOf(dummyVocabulary1, dummyVocabulary2)
    }

    @ApiOperation("모두의 단어장 내려받기")
    @PostMapping("v1/vocabularies/{vocabularyId}")
    fun copyVocabulary(@PathVariable vocabularyId: Long) {

    }

    @ApiOperation("모두의 단어장 폴더 별 내려받기")
    @PostMapping("v1/folders/{folderId}")
    fun copyVocabularies(@PathVariable folderId: Long) {

    }
}