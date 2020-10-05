package com.spark.poingpoing.user

import com.spark.poingpoing.config.BaseEntity
import com.spark.poingpoing.folder.Folder
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*


@Entity
data class User(
        var name: String,

        val credential: String,

        @ColumnDefault("")
        var photoPath: String = ""
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @ColumnDefault("true")
    var active: Boolean = true

    @OneToMany(mappedBy = "user")
    val folders: MutableList<Folder> = mutableListOf()

    fun delete() {
        active = false
    }

    fun modifyName(name: String) {
        this.name = name
    }
}
