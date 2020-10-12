package com.spark.poingpoing.user

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @EntityGraph(attributePaths = ["folders"], type = EntityGraph.EntityGraphType.LOAD)
    override fun findById(userId: Long): Optional<User>

    fun findFirstByEmailAndActiveIsTrue(email: String): Optional<User>
}
