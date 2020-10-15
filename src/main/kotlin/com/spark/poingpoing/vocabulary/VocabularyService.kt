package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.exception.ForbiddenException
import com.spark.poingpoing.exception.NotFoundException
import com.spark.poingpoing.folder.FolderService
import com.spark.poingpoing.photo.PhotoService
import com.spark.poingpoing.user.User
import com.spark.poingpoing.util.convertToPhotoUrl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VocabularyService(
        private val photoService: PhotoService,
        private val folderService: FolderService,
        private val vocabularyRepository: VocabularyRepository) {

    @Transactional
    fun addVocabulary(user: User, vocabularyCreateRequest: VocabularyCreateRequest): VocabularyCreateResponse {
        val photoPath = photoService.uploadPhoto(photo = vocabularyCreateRequest.photo!!)

        val folder = folderService.getFolder(user, vocabularyCreateRequest.folderId)

        val savedVocabulary = vocabularyRepository.save(
                Vocabulary(
                        english = vocabularyCreateRequest.english,
                        korean = vocabularyCreateRequest.korean,
                        photoPath = photoPath,
                        folder = folder,
                        user = user
                ))

        return VocabularyCreateResponse(savedVocabulary.id)
    }

    @Transactional
    fun getMyFolderVocabularies(user: User, folderId: Long, pageRequest: PageRequest): VocabularyPageResponse {
        val folder = folderService.getFolder(user, folderId)
        val vocabularies = vocabularyRepository.findByUserIdAndFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(user.id, folder.id, pageRequest)

        val responses = vocabularies.map {
            VocabularyResponse(it.id, it.english, it.korean, it.photoPath.convertToPhotoUrl())
        }
                .toList()

        return VocabularyPageResponse(responses, vocabularies.hasNext(), vocabularies.totalElements)
    }

    @Transactional
    fun getMyFolderVocabularies(user: User, folderIds: List<Long>): List<VocabularyResponse> {
        folderIds.forEach {
            folderService.getFolder(user, it)
        }

        val vocabularies = vocabularyRepository.findByUserIdAndFolderIdInAndActiveIsTrueOrderByUpdatedAtDesc(user.id, folderIds)

        return vocabularies.map {
            VocabularyResponse(it.id, it.english, it.korean, it.photoPath.convertToPhotoUrl())
        }.toList()
    }

    @Transactional
    fun modifyVocabulary(user: User, vocabularyId: Long, vocabularyUpdateRequest: VocabularyUpdateRequest) {
        val vocabulary = getVocabulary(user, vocabularyId)

        vocabularyUpdateRequest.folderId?.let {
            val folder = folderService.getFolder(user, it)
            vocabulary.modifyFolder(folder)
        }
        vocabularyUpdateRequest.english?.let { vocabulary.english = it }
        vocabularyUpdateRequest.korean?.let { vocabulary.korean = it }
        vocabularyUpdateRequest.photo?.let {
            val newPhotoPath = photoService.uploadPhoto(it)
            vocabulary.photoPath = newPhotoPath
        }

        vocabularyRepository.save(vocabulary)
    }

    @Transactional
    fun deleteVocabulary(user: User, vocabularyId: Long) {
        val vocabulary = getVocabulary(user, vocabularyId)

        vocabulary.delete()
    }

    private fun getVocabulary(user: User, vocabularyId: Long): Vocabulary {
        val vocabulary = getVocabulary(vocabularyId)
        if (vocabulary.user != user) {
            throw ForbiddenException("단어($vocabularyId) 접근이 불가능합니다.")
        }

        return vocabulary
    }

    private fun getVocabulary(vocabularyId: Long): Vocabulary {
        return vocabularyRepository.findByIdAndActiveIsTrue(vocabularyId)
                .orElseThrow { NotFoundException("단어($vocabularyId)를 찾을 수 없습니다.") }
    }
}