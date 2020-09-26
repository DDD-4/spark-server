package com.spark.poingpoing.vocabulary.every

import com.spark.poingpoing.user.LoginUserGetter
import com.spark.poingpoing.vocabulary.VocabularyPageResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@Api(tags = ["Every-Voca"])
@RestController
class EveryVocabularyController(
        private val loginUserGetter: LoginUserGetter,
        private val everyVocabularyService: EveryVocabularyService) {

    @ApiOperation("모두의 단어장 리스트 조회")
    @GetMapping("v1/every-vocabularies")
    fun getEveryVocabularyFolders(@RequestParam(defaultValue = "LATEST") sortType: SortType,
                                  @RequestParam(defaultValue = "0") page: Int,
                                  @RequestParam(defaultValue = "10") size: Int): EveryVocabularyPageResponse {
        val user = loginUserGetter.getLoginUser()

        if (SortType.LATEST == sortType) {
            return everyVocabularyService.getEveryVocabularyFoldersOrderByLatest(user, PageRequest.of(page, size))
        }

        return everyVocabularyService.getEveryVocabularyFoldersOrderByPopular(user, PageRequest.of(page, size))
    }

    @ApiOperation("모두의 단어장 폴더 별 조회")
    @GetMapping("v1/every-vocabularies/folders/{folderId}")
    fun getEveryVocabularies(@PathVariable folderId: Long,
                             @RequestParam(defaultValue = "0") page: Int,
                             @RequestParam(defaultValue = "10") size: Int): VocabularyPageResponse {
        val user = loginUserGetter.getLoginUser()

        return everyVocabularyService.getEveryVocabularies(user, folderId, PageRequest.of(page, size))
    }

    @ApiOperation("모두의 단어장 Id 별 내려받기")
    @PostMapping("v1/every-vocabularies")
    fun copyEveryVocabularies(@RequestBody everyVocabularyRequest: EveryVocabularyIdsRequest) {
        val user = loginUserGetter.getLoginUser()

        everyVocabularyService.copyEveryVocabularies(user, everyVocabularyRequest)
    }

    @ApiOperation("모두의 단어장 폴더 별 내려받기")
    @PostMapping("v1/every-vocabularies/folders/{folderId}")
    fun copyVocabularies(@PathVariable folderId: Long, @RequestBody everyVocabularyRequest: EveryVocabularyRequest) {
        val user = loginUserGetter.getLoginUser()

        everyVocabularyService.copyEveryVocabularyFolder(user, folderId, everyVocabularyRequest.myFolderId)
    }
}