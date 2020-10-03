package com.spark.poingpoing.photo

import com.spark.poingpoing.config.BaseEntity
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class Photo(
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "BINARY(16)")
        val pathKey: UUID = UUID.randomUUID(),

        val name: String,

        val extension: String
) : BaseEntity() {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L
}