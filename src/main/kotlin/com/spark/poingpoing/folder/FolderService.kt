package com.spark.poingpoing.folder

import com.spark.poingpoing.user.User
import javassist.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FolderService(private val folderRepository: FolderRepository) {

    @Transactional(readOnly = true)
    fun getFolder(folderId: Long): Folder {
        return folderRepository.findByIdAndActiveIsTrue(folderId)
                .orElseThrow { NotFoundException("해당 폴더를 찾을 수 없습니다.") }
    }

    @Transactional
    fun createFolder(user: User, folderRequest: FolderRequest) {
        val countOfActiveFolder = folderRepository.countByUserIdAndActiveIsTrueAndDefaultIsFalse(user.id)
                .toInt()

        val folder = Folder(name = folderRequest.name!!,
                sharable = folderRequest.shareable!!,
                user = user,
                priority = countOfActiveFolder + 1)

        folderRepository.save(folder)

        user.addFolder(folder)
    }

    @Transactional
    fun modifyFolder(folderId: Long, folderRequest: FolderRequest) {
        val folder = getFolder(folderId)
        if(folder.active) {
            throw IllegalArgumentException("기본 폴더는 수정할 수 없습니다.")
        }

        folderRequest.shareable?.let { folder.sharable = it }
        folderRequest.name?.let { folder.name = it }
    }

    @Transactional
    fun modifyFoldersOrder(user: User, folderIds: List<Long>) {
        val countOfActiveFolder = folderRepository.countByUserIdAndActiveIsTrueAndDefaultIsFalse(user.id)
                .toInt()
        if (countOfActiveFolder != folderIds.size) {
            throw IllegalArgumentException("변경할 폴더의 갯수가 맞지 않습니다.")
        }

        val folders = folderRepository.findAllById(folderIds)
        if (folders.any { it.default }) {
            throw IllegalArgumentException("기본 폴더는 수정할 수 없습니다.")
        }

        if (folders.any { !it.active }) {
            throw IllegalArgumentException("삭제된 폴더의 순서는 바꿀 수 없습니다.")
        }

        folderIds.forEachIndexed { index, folderId ->
            val folder = getFolder(folderId)
            folder.priority = index + 1
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

    @Transactional
    fun getFolders(user: User): List<FolderResponse> {
        val folders = folderRepository.findByUserIdAndActiveIsTrue(user.id)

        return folders
                .sortedBy {
                    it.default
                    it.priority
                }
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
