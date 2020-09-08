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
    fun createFolder(@RequestBody folderRequest: FolderRequest) {
        val user = loginUserGetter.getLoginUser()

        folderService.createFolder(user, folderRequest)
    }

    @ApiOperation("나의 폴더 수정")
    @PatchMapping("v1/folders/{folderId}")
    fun modifyVocabulary(@PathVariable folderId: Long,
                         @RequestBody folderRequest: FolderRequest) =
            folderService.modifyFolder(folderId, folderRequest)

    @ApiOperation("나의 폴더 순서 변경")
    @PutMapping("v1/folders")
    fun modifyOrders(@RequestBody orders: List<Long>) {
        val user = loginUserGetter.getLoginUser()

        folderService.modifyFoldersOrder(user, orders)
    }

    @ApiOperation("나의 폴더 삭제")
    @DeleteMapping("v1/folders")
    fun deleteFolder(@RequestParam folderIds: List<Long>) {
        val user = loginUserGetter.getLoginUser()

        folderService.deleteFolders(user, folderIds)
    }


    @ApiOperation("나의 폴더 조회")
    @GetMapping("v1/folders")
    fun getAllMyFolders(): List<FolderResponse> {
        return loginUserGetter.getLoginUser().getActiveFolders()
                .sortedBy { it.priority }
                .map {
                    FolderResponse(
                            id = it.id,
                            name = it.name,
                            shareable = it.sharable,
                            default = it.default
                    )
                }
                .toList()
    }
}