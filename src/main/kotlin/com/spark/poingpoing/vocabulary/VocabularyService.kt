package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.folder.FolderService
import com.spark.poingpoing.photo.PhotoService
import com.spark.poingpoing.user.User
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VocabularyService(
        private val photoService: PhotoService,
        private val folderService: FolderService,
        private val vocabularyRepository: VocabularyRepository) {

    @Transactional
    fun addVocabulary(user: User, vocabularyRequest: VocabularyRequest) {
        val photo = photoService.uploadPhoto(vocabularyRequest.photo!!)

        val folder = vocabularyRequest.folderId?.let { folderService.getFolder(it) }

        val vocabulary = Vocabulary(
                english = vocabularyRequest.english!!,
                korean = vocabularyRequest.korean!!,
                photo = photo,
                folder = folder,
                user = user
        )

        folder?.addVocabulary(vocabulary)
    }

    @Transactional
    fun modifyVocabulary(vocabularyId: Long, vocabularyRequest: VocabularyRequest) {
        val vocabulary = getVocabulary(vocabularyId)

        vocabularyRequest.folderId?.let {
            val folder = folderService.getFolder(folderId = it)
            vocabulary.modifyFolder(folder)
        }
        vocabularyRequest.english?.let { vocabulary.english = it }
        vocabularyRequest.korean?.let { vocabulary.korean = it }
        vocabularyRequest.photo?.let {
            val newPhoto = photoService.uploadPhoto(it)
            vocabulary.photo = newPhoto
        }
    }

    @Transactional
    fun deleteVocabulary(vocabularyId: Long) {
        val vocabulary = getVocabulary(vocabularyId)

        vocabulary.delete()
    }

    @Transactional
    fun getMyFolderVocabularies(user: User, folderId: Long?, of: PageRequest): List<VocabularyResponse> {
        // todo
        return listOf()
    }

    private fun getVocabulary(vocabularyId: Long): Vocabulary {
        return vocabularyRepository.findById(vocabularyId)
                .orElseThrow { IllegalArgumentException("단어를 찾을 수 없습니다.") }
    }
}

