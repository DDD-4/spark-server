package com.spark.poingpoing.vocabulary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VocabularyRepository : JpaRepository<Vocabulary, Long>