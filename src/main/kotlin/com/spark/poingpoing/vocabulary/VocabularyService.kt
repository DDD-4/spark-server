package com.spark.poingpoing.vocabulary

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
    fun addVocabulary(user: User, vocabularyRequest: VocabularyRequest) : VocabularyCreateResponse {
        val photoPath = photoService.uploadPhoto(vocabularyRequest.photo!!)

        val folder = vocabularyRequest.folderId!!.let { folderService.getFolder(it) }

        val vocabulary = Vocabulary(
                english = vocabularyRequest.english!!,
                korean = vocabularyRequest.korean!!,
                photoPath = photoPath,
                folder = folder,
                user = user
        )

        val savedVocabulary = vocabularyRepository.save(vocabulary)

        return VocabularyCreateResponse(savedVocabulary.id)
    }

    @Transactional
    fun getMyFolderVocabularies(user: User, folderId: Long, pageRequest: PageRequest): VocabularyPageResponse {
        val vocabularies = vocabularyRepository.findByUserIdAndFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(user.id , folderId, pageRequest)

        val responses = vocabularies.map {
            VocabularyResponse(it.id, it.english, it.korean, it.photoPath.convertToPhotoUrl())
        }
                .toList()

        return VocabularyPageResponse(responses, vocabularies.hasNext(), vocabularies.totalElements)
    }

    @Transactional
    fun modifyVocabulary(user: User, vocabularyId: Long, vocabularyRequest: VocabularyRequest) {
        val vocabulary = getVocabulary(vocabularyId)
        if(vocabulary.user.id != user.id) {
            throw IllegalArgumentException("나의 단어만 수정이 가능합니다.")
        }
        vocabularyRequest.folderId?.let {
            val folder = folderService.getFolder(folderId = it)
            vocabulary.modifyFolder(folder)
        }
        vocabularyRequest.english?.let { vocabulary.english = it }
        vocabularyRequest.korean?.let { vocabulary.korean = it }
        vocabularyRequest.photo?.let {
            val newPhotoPath = photoService.uploadPhoto(it)
            vocabulary.photoPath = newPhotoPath
        }
    }

    @Transactional
    fun deleteVocabulary(user: User, vocabularyId: Long) {
        val vocabulary = getVocabulary(vocabularyId)
        if(vocabulary.user.id != user.id) {
            throw IllegalArgumentException("나의 단어만 삭제가 가능합니다.")
        }

        vocabulary.delete()
    }

    private fun getVocabulary(vocabularyId: Long): Vocabulary {
        return vocabularyRepository.findById(vocabularyId)
                .orElseThrow { IllegalArgumentException("단어를 찾을 수 없습니다.") }
    }
}