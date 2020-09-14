package com.spark.poingpoing.vocabulary

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VocabularyRepository : JpaRepository<Vocabulary, Long> {
    fun findByUserIdAndFolderIdOrderByUpdatedAtDesc(userId: Long, folderId: Long, pageable: Pageable): Page<Vocabulary>
    fun findByUserIdNotAndFolderIdOrderByUpdatedAtDesc(userId: Long, folderId: Long, pageable: Pageable): Page<Vocabulary>
    fun findAllByFolderIdOrderByUpdatedAtDesc(folderId: Long): List<Vocabulary>
}