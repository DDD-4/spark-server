package com.spark.poingpoing.util

import com.spark.poingpoing.exception.NotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test



class FileUtilsKtTest {

    @Test
    fun findFileWithNotFoundException() {
        assertThrows(NotFoundException::class.java) {
            findFile("1234566")
        }
    }

    @Test
    fun getUUIDWithDash() {
        val result = "b2f6e721aeef478fb81e942c705071bc".toUUIDStringWithDash()

        assertEquals("b2f6e721-aeef-478f-b81e-942c705071bc", result)
    }
}
