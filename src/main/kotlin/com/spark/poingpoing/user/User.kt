package com.spark.poingpoing.user

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.folder.Folder
import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val name: String,

        @OneToMany
        val folders: List<Folder> = listOf()
): BaseEntity()
