package com.spark.poingpoing.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class PhotoUrlUtilsKtTest {

    @Test
    fun cretePath() {
        val photoUrl = "1".convertToPhotoUrl()

        assertEquals("http://www.poingpoing.cf/v1/photos/1", photoUrl)
    }
}
