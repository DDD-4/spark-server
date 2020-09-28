package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.user.LoginUserGetter
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@Api(tags = ["Voca"])
@RestController
class VocabularyController(
        private val loginUserGetter: LoginUserGetter,
        private val vocabularyService: VocabularyService) {

    @ApiOperation("나의 단어 등록")
    @PostMapping("v1/vocabularies")
    fun createVocabulary(vocabularyRequest: VocabularyRequest): VocabularyCreateResponse {
        val user = loginUserGetter.getLoginUser()

        return vocabularyService.addVocabulary(user, vocabularyRequest)
    }

    @ApiOperation("나의 단어 목록 조회")
    @GetMapping("v1/vocabularies")
    fun getMyFolderVocabularies(@RequestParam(required = true) folderId: Long,
                                @RequestParam(defaultValue = "0", required = false) page: Int,
                                @RequestParam(defaultValue = "10", required = false) size: Int): VocabularyPageResponse {
        val user = loginUserGetter.getLoginUser()

        return vocabularyService.getMyFolderVocabularies(user, folderId, PageRequest.of(page, size))
    }

    @ApiOperation("나의 단어 수정")
    @PatchMapping("v1/vocabularies/{vocabularyId}")
    fun modifyVocabulary(@PathVariable vocabularyId: Long,
                         @RequestBody vocabularyRequest: VocabularyRequest) {
        val user = loginUserGetter.getLoginUser()

        vocabularyService.modifyVocabulary(user, vocabularyId, vocabularyRequest)
    }

    @ApiOperation("나의 단어 삭제")
    @DeleteMapping("v1/vocabularies/{vocabularyId}")
    fun deleteVocabulary(@PathVariable vocabularyId: Long) {
        val user = loginUserGetter.getLoginUser()

        vocabularyService.deleteVocabulary(user, vocabularyId)
    }
}