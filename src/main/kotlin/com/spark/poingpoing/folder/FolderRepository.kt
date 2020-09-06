package com.spark.poingpoing.folder

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FolderRepository : JpaRepository<Folder, Long>
