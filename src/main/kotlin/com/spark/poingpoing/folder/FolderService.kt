package com.spark.poingpoing.folder

import com.spark.poingpoing.user.User
import javassist.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FolderService(private val folderRepository: FolderRepository) {

    @Transactional(readOnly = true)
    fun getFolder(folderId: Long): Folder {
        return folderRepository.findById(folderId)
                .orElseThrow { NotFoundException("해당 폴더를 찾을 수 없습니다.") }
    }

    @Transactional
    fun createFolder(user: User, folderRequest: FolderRequest) {

        val folder = Folder(name = folderRequest.name!!,
                sharable = folderRequest.shareable!!,
                user = user,
                priority = user.folders.size)

        folderRepository.save(folder)

        user.addFolder(folder)
    }

    @Transactional
    fun modifyFolder(folderId: Long, folderRequest: FolderRequest) {
        val folder = getFolder(folderId)

        folderRequest.shareable?.let { folder.sharable = it }
        folderRequest.name?.let { folder.name = it }
    }

    @Transactional
    fun modifyFoldersOrder(user: User, forderIds: List<Long>) {
        if(user.getActiveFolders().size != forderIds.size) {
            throw IllegalArgumentException("변경할 폴더의 갯수가 맞지 않습니다.")
        }

        forderIds.forEachIndexed { index, folderId ->
            val folder = getFolder(folderId)
            folder.priority = index
        }
    }

    @Transactional
    fun deleteFolders(user: User, folderIds: List<Long>) {
        folderIds.map {
            val folder = getFolder(it)

            folder.removeFolder()
            user.removeFolder(folder)
        }
    }
}
