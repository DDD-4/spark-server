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

        @OneToMany(mappedBy = "user")
        val folders: MutableList<Folder> = mutableListOf()

) : BaseEntity() {
    fun addFolder(folder: Folder) {
        if (folders.contains(folder)) {
            throw IllegalArgumentException("이미 존재하는 폴더입니다.")
        }

        this.folders.add(folder)
    }

    fun removeFolder(folder: Folder) {
        folders.remove(folder)
    }
}
