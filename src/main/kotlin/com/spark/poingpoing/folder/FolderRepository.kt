package com.spark.poingpoing.folder

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FolderRepository : JpaRepository<Folder, Long> {
    fun findByIdAndActiveIsTrue(id: Long): Optional<Folder>
    fun findByUserIdAndActiveIsTrue(userId: Long): List<Folder>
    fun countByUserIdAndActiveIsTrue(userId: Long): Long
}
