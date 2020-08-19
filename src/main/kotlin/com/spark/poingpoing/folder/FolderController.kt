package com.spark.poingpoing.folder

import com.spark.poingpoing.vocabulary.VocabularyRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*


@Api(tags = ["Folder"])
@RestController
class FolderController {

    @ApiOperation("나의 단어 등록")
    @PostMapping("v1/folders")
    fun createFolder(@RequestBody vocabularyRequest: VocabularyRequest) {

    }

    @ApiOperation("나의 폴더 수정")
    @PatchMapping("v1/folders/{folderId}")
    fun modifyVocabulary(@PathVariable folderId: Long,
                         @RequestBody folderRequest: FolderRequest) {

    }

    @ApiOperation("나의 폴더 순서 변경")
    @PutMapping("v1/folders")
    fun modifyOrders(@RequestBody orders: List<Long>) {

    }

    @ApiOperation("나의 폴더 삭제")
    @DeleteMapping("v1/folders/{folderId}")
    fun deleteFolder(@PathVariable folderId: Long) {

    }

    @ApiOperation("나의 폴더 조회")
    @GetMapping("v1/folders")
    fun getAllMyFolders(): List<FolderResponse> {

        return listOf()
    }
}