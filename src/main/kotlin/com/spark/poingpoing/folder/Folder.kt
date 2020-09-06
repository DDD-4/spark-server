package com.spark.poingpoing.folder

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.user.User
import com.spark.poingpoing.vocabulary.Vocabulary
import javax.persistence.*

@Entity
data class Folder(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        val name: String,

        val priority: Int,

        val sharable: Boolean,

        val active: Boolean,

        @OneToMany
        val vocabularies: List<Vocabulary> = listOf()
) : BaseEntity()