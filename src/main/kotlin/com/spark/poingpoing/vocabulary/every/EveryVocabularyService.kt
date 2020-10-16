package com.spark.poingpoing.vocabulary.every

import com.spark.poingpoing.folder.FolderService
import com.spark.poingpoing.user.User
import com.spark.poingpoing.util.convertToPhotoUrl
import com.spark.poingpoing.vocabulary.Vocabulary
import com.spark.poingpoing.vocabulary.VocabularyPageResponse
import com.spark.poingpoing.vocabulary.VocabularyRepository
import com.spark.poingpoing.vocabulary.VocabularyResponse
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EveryVocabularyService(
        private val folderService: FolderService,
        private val vocabularyRepository: VocabularyRepository,
        private val everyVocabularyDao: EveryVocabularyDao) {

    @Transactional(readOnly = true)
    fun getEveryVocabularyFoldersOrderByLatest(user: User, pageRequest: PageRequest): EveryVocabularyPageResponse {

        val everyVocabularies = everyVocabularyDao.findEveryVocabulariesOrderByLatest(user.id, pageRequest)
        everyVocabularies.content
                .forEach {
                    it.photoUrl = it.photoUrl.convertToPhotoUrl()
                    it.userPhotoUrl = it.userPhotoUrl.convertToPhotoUrl()

                }
        return EveryVocabularyPageResponse(everyVocabularies.content, everyVocabularies.hasNext(), everyVocabularies.totalElements)
    }

    @Transactional(readOnly = true)
    fun getEveryVocabularyFoldersOrderByPopular(user: User, pageRequest: PageRequest): EveryVocabularyPageResponse {
        val everyVocabularies = everyVocabularyDao.findEveryVocabulariesOrderByPopular(user.id, pageRequest)
        everyVocabularies.content
                .forEach {
                    it.photoUrl = it.photoUrl.convertToPhotoUrl()
                    it.userPhotoUrl = it.userPhotoUrl.convertToPhotoUrl()
                }
        return EveryVocabularyPageResponse(everyVocabularies.content, everyVocabularies.hasNext(), everyVocabularies.totalElements)
    }

    @Transactional(readOnly = true)
    fun getEveryVocabularies(user: User, folderId: Long, pageRequest: PageRequest): VocabularyPageResponse {
        val vocabularies = vocabularyRepository.findByUserIdNotAndFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(user.id, folderId, pageRequest)

        val vocabulariesResponse = vocabularies.map {
            VocabularyResponse(it.id, it.english, it.korean, it.photoPath.convertToPhotoUrl())
        }
                .toList()

        return VocabularyPageResponse(vocabulariesResponse, vocabularies.hasNext(), vocabularies.totalElements)
    }

    @Transactional
    fun copyEveryVocabularies(user: User, everyVocabularyIdsRequest: EveryVocabularyIdsRequest) {
        val targetFolder = folderService.getFolder(everyVocabularyIdsRequest.myFolderId)
        val vocabularies = vocabularyRepository.findAllById(everyVocabularyIdsRequest.vocabularyIds)

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

        folderService.plusSharePoint(vocabularies[0].folder!!.id)
    }

    @Transactional
    fun copyEveryVocabularyFolder(user: User, folderId: Long, myFolderId: Long) {
        val targetFolder = folderService.getFolder(myFolderId)
        val vocabularies = vocabularyRepository.findAllByFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(folderId)

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

        folderService.plusSharePoint(folderId)
    }
}

