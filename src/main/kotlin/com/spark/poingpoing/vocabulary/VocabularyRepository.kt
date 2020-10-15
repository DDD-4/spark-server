package com.spark.poingpoing.vocabulary

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VocabularyRepository : JpaRepository<Vocabulary, Long> {
    fun findByUserIdAndFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(userId: Long, folderId: Long, pageable: Pageable): Page<Vocabulary>
    fun findByUserIdAndFolderIdInAndActiveIsTrueOrderByUpdatedAtDesc(userId: Long, folderIds: List<Long>): List<Vocabulary>
    fun findByUserIdNotAndFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(userId: Long, folderId: Long, pageable: Pageable): Page<Vocabulary>
    fun findAllByFolderIdAndActiveIsTrueOrderByUpdatedAtDesc(folderId: Long): List<Vocabulary>
    fun findByIdAndActiveIsTrue(vocabularyId: Long): Optional<Vocabulary>
}