package com.spark.poingpoing.folder

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.user.User
import javax.persistence.*

@Entity
data class Folder(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @ManyToOne
        val user: User,

        val name: String,

        val priority: Int,

        val sharable: Boolean,

        val active: Boolean
) : BaseEntity()