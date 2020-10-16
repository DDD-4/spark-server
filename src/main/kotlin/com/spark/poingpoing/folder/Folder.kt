package com.spark.poingpoing.folder

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.exception.BadRequestException
import com.spark.poingpoing.user.User
import com.spark.poingpoing.util.convertToPhotoUrl
import com.spark.poingpoing.vocabulary.Vocabulary
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
data class Folder(
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        var name: String,

        var priority: Int,

        var sharable: Boolean,

        @ColumnDefault("0.0")
        var point: Double = 0.0,

        val basic: Boolean = false,

        @OneToMany(mappedBy = "folder")
        val vocabularies: MutableList<Vocabulary> = mutableListOf()
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var active: Boolean = true

    fun removeVocabulary(vocabulary: Vocabulary) {
        vocabularies.minus(vocabulary)
    }

    fun removeFolder() {
        if (basic) {
            throw BadRequestException("기본 폴더는 삭제할 수 없습니다.")
        }

        if (active) {
            active = false
        }
    }

    fun plusSharePoint() {
        point += 1
    }

    fun getRepresentativePhotoUrl(): String? {
        val activeVocabularies = this.vocabularies.filter { vocabulary -> vocabulary.active }

        if (activeVocabularies.isEmpty()) {
            return ""
        }

        return activeVocabularies
                .asSequence()
                .sortedByDescending { it.updatedAt }
                .first()
                .photoPath
                .convertToPhotoUrl()
    }
}