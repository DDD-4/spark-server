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
) : BaseEntity() {
    fun addFolder(folder: Folder) {
        if (folders.contains(folder)) {
            throw IllegalArgumentException("이미 존재하는 폴더입니다.")
        }

        folders.plus(folder)
    }

    fun removeFolder(folder: Folder) {
        folders.minus(folder)
    }
}
