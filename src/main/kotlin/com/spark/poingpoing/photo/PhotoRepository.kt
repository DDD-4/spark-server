package com.spark.poingpoing.photo

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PhotoRepository : JpaRepository<Photo, UUID>
