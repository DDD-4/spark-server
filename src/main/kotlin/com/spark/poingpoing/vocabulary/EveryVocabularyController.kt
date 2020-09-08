package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.user.LoginUserGetter
import com.spark.poingpoing.vocabulary.VocabularyResponse
import com.spark.poingpoing.vocabulary.VocabularyService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@Api(tags = ["Voca"])
@RestController
class EveryVocabularyController(
        private val loginUserGetter: LoginUserGetter,
        private val vocabularyService: VocabularyService) {

    @ApiOperation("모두의 단어장 조회")
    @GetMapping("v1/every-vocabularies")
    fun getAllVocabularies(): List<VocabularyResponse> {
        // todo pageable, delete dummy, 나의 단어 이외 조회
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
    @PostMapping("v1/every-vocabularies")
    fun copyVocabulary(@RequestBody vocabularyIds: List<Long>) {
        //todo copy의 개념이므로 중복체크 X
    }

    @ApiOperation("모두의 단어장 폴더 별 내려받기")
    @PostMapping("v1/every-vocabularies/folders/{folderId}")
    fun copyVocabularies(@PathVariable folderId: Long) {
        //todo copy의 개념이므로 중복체크 X
    }
}