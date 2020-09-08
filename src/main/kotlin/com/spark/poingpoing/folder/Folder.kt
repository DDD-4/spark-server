package com.spark.poingpoing.folder

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.user.User
import com.spark.poingpoing.vocabulary.Vocabulary
import javax.persistence.*

@Entity
data class Folder(
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        var name: String,

        var priority: Int,

        var sharable: Boolean,

        val default: Boolean = false,

        @OneToMany
        val vocabularies: MutableList<Vocabulary> = mutableListOf()
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var active: Boolean = true

    fun addVocabulary(vocabulary: Vocabulary) {
        if (vocabularies.contains(vocabulary)) {
            throw IllegalArgumentException("이미 존재하는 단어입니다.")
        }

        vocabularies.add(vocabulary)
    }

    fun removeVocabulary(vocabulary: Vocabulary) {
        vocabularies.minus(vocabulary)
    }

    fun removeFolder() {
        if(default) {
            throw IllegalArgumentException("기본 폴더는 삭제할 수 없습니다.")
        }

        if(active) {
            active = false
        }
    }
}