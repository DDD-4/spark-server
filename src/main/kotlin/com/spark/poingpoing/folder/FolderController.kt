package com.spark.poingpoing.folder

import com.spark.poingpoing.user.LoginUserGetter
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*


@Api(tags = ["Folder"])
@RestController
class FolderController(private val loginUserGetter: LoginUserGetter,
                       private val folderService: FolderService) {

    @ApiOperation("나의 폴더 등록")
    @PostMapping("v1/folders")
    fun createFolder(@RequestBody folderCreateRequest: FolderCreateRequest): FolderCreateResponse {
        val user = loginUserGetter.getLoginUser()

        return folderService.createFolder(user, folderCreateRequest)
    }

    @ApiOperation("나의 폴더 수정")
    @PatchMapping("v1/folders/{folderId}")
    fun modifyVocabulary(@PathVariable folderId: Long,
                         @RequestBody folderUpdateRequest: FolderUpdateRequest) {
        val user = loginUserGetter.getLoginUser()

        folderService.modifyFolder(user, folderId, folderUpdateRequest)
    }


    @ApiOperation("나의 폴더 순서 변경")
    @PutMapping("v1/folders")
    fun modifyOrders(@RequestBody updateDeleteFolderRequest: UpdateDeleteFolderRequest) {
        val user = loginUserGetter.getLoginUser()

        folderService.modifyFoldersOrder(user, updateDeleteFolderRequest.folderIds)
    }

    @ApiOperation("나의 폴더 삭제")
    @DeleteMapping("v1/folders")
    fun deleteFolder(@RequestBody updateDeleteFolderRequest: UpdateDeleteFolderRequest) {
        val user = loginUserGetter.getLoginUser()

        folderService.deleteFolders(user, updateDeleteFolderRequest.folderIds)
    }

    @ApiOperation("나의 폴더 조회")
    @GetMapping("v1/folders")
    fun getAllMyFolders(): List<FolderResponse> {
        val user = loginUserGetter.getLoginUser()

        return folderService.getFolders(user)
    }
}