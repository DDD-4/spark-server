package com.spark.poingpoing.vocabulary.every

import com.spark.poingpoing.folder.FolderService
import com.spark.poingpoing.user.User
import com.spark.poingpoing.util.convertToPhotoUrl
import com.spark.poingpoing.vocabulary.Vocabulary
import com.spark.poingpoing.vocabulary.VocabularyRepository
import com.spark.poingpoing.vocabulary.VocabularyResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EveryVocabularyService(
        private val folderService: FolderService,
        private val vocabularyRepository: VocabularyRepository,
        private val everyVocabularyDao: EveryVocabularyDao) {

    @Transactional(readOnly = true)
    fun getEveryVocabularyFoldersOrderByLatest(user: User, pageRequest: PageRequest): Page<EveryVocabularyResponse> {

        return everyVocabularyDao.findEveryVocabularies(user.id, pageRequest)
    }

    @Transactional(readOnly = true)
    fun getEveryVocabularyFoldersOrderByPopular(user: User, of: PageRequest): Page<EveryVocabularyResponse> {

        return Page.empty()
    }

    @Transactional(readOnly = true)
    fun getEveryVocabularies(user: User, folderId: Long, pageRequest: PageRequest): List<VocabularyResponse> {
        val vocabularies = vocabularyRepository.findByUserIdNotAndFolderIdOrderByUpdatedAtDesc(user.id, folderId, pageRequest)

        return vocabularies.map {
            VocabularyResponse(it.id, it.english, it.korean, it.photoPath.convertToPhotoUrl())
        }
                .toList()
    }

    @Transactional
    fun copyEveryVocabularies(user: User, everyVocabularyRequest: EveryVocabularyRequest) {
        val targetFolder = folderService.getFolder(everyVocabularyRequest.myFolderId)
        val vocabularies = vocabularyRepository.findAllById(everyVocabularyRequest.vocabularyIds!!)

        val copyVocabularies = vocabularies
                .map {
                    Vocabulary(
                            it.english,
                            it.korean,
                            it.photoPath,
                            user,
                            targetFolder
                    )
                }
                .toList()

        vocabularyRepository.saveAll(copyVocabularies)
    }

    @Transactional
    fun copyEveryVocabularyFolder(user: User, folderId: Long, myFolderId: Long) {
        val targetFolder = folderService.getFolder(myFolderId)
        val vocabularies = vocabularyRepository.findAllByFolderIdOrderByUpdatedAtDesc(folderId)

        val copyVocabularies = vocabularies
                .map {
                    Vocabulary(
                            it.english,
                            it.korean,
                            it.photoPath,
                            user,
                            targetFolder
                    )
                }
                .toList()

        vocabularyRepository.saveAll(copyVocabularies)
    }
}

