package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.folder.Folder
import com.spark.poingpoing.photo.Photo
import com.spark.poingpoing.user.User
import javax.persistence.*

@Entity
data class Vocabulary(
        var english: String,

        var korean: String,

        var photoPath: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "folder_id")
        var folder: Folder? = null
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var active: Boolean = true

    fun delete() {
        if (active) {
            active = false
        }
    }
}