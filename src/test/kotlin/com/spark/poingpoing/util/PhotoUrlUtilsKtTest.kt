package com.spark.poingpoing.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class PhotoUrlUtilsKtTest {

    @Test
    fun cretePath() {
        val photoUrl = "b2f6e721aeef478fb81e942c705071bb".convertToPhotoUrl()

        assertEquals("http://www.poingpoing.cf/v1/photos/b2f6e721-aeef-478f-b81e-942c705071bb", photoUrl)
    }
}
