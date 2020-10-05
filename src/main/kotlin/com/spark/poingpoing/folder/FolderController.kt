package com.spark.poingpoing.folder

import com.spark.poingpoing.user.LoginUserGetter
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@Api(tags = ["Folder"])
@RestController
class FolderController(private val loginUserGetter: LoginUserGetter,
                       private val folderService: FolderService) {

    @ApiOperation("나의 폴더 등록")
    @PostMapping("v1/folders")
    fun createFolder(httpServletRequest: HttpServletRequest,
                     @RequestBody folderCreateRequest: FolderCreateRequest): FolderCreateResponse {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        return folderService.createFolder(user, folderCreateRequest)
    }

    @ApiOperation("나의 폴더 수정")
    @PatchMapping("v1/folders/{folderId}")
    fun modifyVocabulary(httpServletRequest: HttpServletRequest,
                         @PathVariable folderId: Long,
                         @RequestBody folderUpdateRequest: FolderUpdateRequest) {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        folderService.modifyFolder(user, folderId, folderUpdateRequest)
    }


    @ApiOperation("나의 폴더 순서 변경")
    @PutMapping("v1/folders")
    fun modifyOrders(httpServletRequest: HttpServletRequest,
                     @RequestBody updateDeleteFolderRequest: UpdateDeleteFolderRequest) {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        folderService.modifyFoldersOrder(user, updateDeleteFolderRequest.folderIds)
    }

    @ApiOperation("나의 폴더 삭제")
    @DeleteMapping("v1/folders")
    fun deleteFolder(httpServletRequest: HttpServletRequest,
                     @RequestBody updateDeleteFolderRequest: UpdateDeleteFolderRequest) {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        folderService.deleteFolders(user, updateDeleteFolderRequest.folderIds)
    }

    @ApiOperation("나의 폴더 조회")
    @GetMapping("v1/folders")
    fun getAllMyFolders(httpServletRequest: HttpServletRequest): List<FolderResponse> {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        return folderService.getFolders(user)
    }
}