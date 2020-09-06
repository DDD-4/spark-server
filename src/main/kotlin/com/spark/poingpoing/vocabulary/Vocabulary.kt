package com.spark.poingpoing.vocabulary

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.folder.Folder
import com.spark.poingpoing.photo.Photo
import com.spark.poingpoing.user.User
import javax.persistence.*


@Entity
data class Vocabulary(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val english: String,

        val korean: String,

        @OneToOne
        val photo: Photo,

        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val folder: Folder,

        val active: Boolean
) : BaseEntity()