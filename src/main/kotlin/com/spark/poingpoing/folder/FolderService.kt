package com.spark.poingpoing.folder

import com.spark.poingpoing.exception.BadRequestException
import com.spark.poingpoing.exception.ForbiddenException
import com.spark.poingpoing.exception.NotFoundException
import com.spark.poingpoing.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FolderService(private val folderRepository: FolderRepository) {

    @Transactional(readOnly = true)
    fun getFolder(user: User, folderId: Long): Folder {
        val folder = getFolder(folderId)
        if (folder.user != user) {
            throw ForbiddenException("폴더($folderId) 접근이 불가능합니다.")
        }

        return folder
    }

    @Transactional(readOnly = true)
    fun getFolder(folderId: Long): Folder {
        return folderRepository.findByIdAndActiveIsTrue(folderId)
                .orElseThrow { NotFoundException("폴더($folderId)를 찾을 수 없습니다.") }
    }

    @Transactional
    fun createFolder(user: User, folderRequest: FolderCreateRequest): FolderCreateResponse {
        val countOfActiveFolder = folderRepository.countByUserIdAndActiveIsTrueAndDefaultIsFalse(user.id)
                .toInt()

        val folder = Folder(name = folderRequest.name,
                sharable = folderRequest.shareable,
                user = user,
                priority = countOfActiveFolder + 1)

        val savedFolder = folderRepository.save(folder)

        return FolderCreateResponse(savedFolder.id)
    }

    @Transactional
    fun createDefaultFolder(user: User) {
        val folder = Folder(name = "기본폴더",
                sharable = false,
                user = user,
                priority = 0,
                default = true)

        folderRepository.save(folder)
    }


    @Transactional
    fun modifyFolder(user: User, folderId: Long, folderUpdateRequest: FolderUpdateRequest) {
        val folder = getFolder(user, folderId)
        if (folder.default) {
            throw BadRequestException("기본 폴더(${folderId})는 수정할 수 없습니다.")
        }

        folderUpdateRequest.shareable?.let { folder.sharable = it }
        folderUpdateRequest.name?.let { folder.name = it }
    }

    @Transactional
    fun modifyFoldersOrder(user: User, folderIds: List<Long>) {
        val countOfActiveFolder = folderRepository.countByUserIdAndActiveIsTrueAndDefaultIsFalse(user.id)
                .toInt()
        if (countOfActiveFolder != folderIds.size) {
            throw BadRequestException("변경할 폴더의 갯수가 맞지 않습니다.")
        }

        val folders = folderRepository.findAllById(folderIds)
        if (folders.any { it.default }) {
            throw BadRequestException("기본 폴더는 수정할 수 없습니다.")
        }

        if (folders.any { !it.active }) {
            throw NotFoundException("폴더를 찾을 수 없습니다.")
        }

        folderIds.forEachIndexed { index, folderId ->
            val folder = getFolder(user, folderId)
            folder.priority = index + 1
        }
    }

    @Transactional
    fun deleteFolders(user: User, folderIds: List<Long>) {
        folderIds.map {
            val folder = getFolder(user, it)

            folder.removeFolder()
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
                            default = it.default,
                            photoUrl = it.getRepresentativePhotoUrl()
                    )
                }
                .toList()
    }

    @Transactional
    fun plusSharePoint(folderId: Long) {
        val folder = getFolder(folderId)

        folder.plusSharePoint()
    }
}
