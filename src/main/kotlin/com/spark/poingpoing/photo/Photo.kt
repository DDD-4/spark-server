package com.spark.poingpoing.photo

import com.spark.poingpoing.config.BaseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Photo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val photoName: String,

        val photoOriginalName: Photo
) : BaseEntity()