package com.spark.poingpoing.util

private const val PHOTO_SERVER_HOST = "http://www.poingpoing.cf/v1/photos"

fun String.convertToPhotoUrl(): String {
    if (this.isEmpty()) {
        return ""
    }

    return "$PHOTO_SERVER_HOST/${this.toUUIDStringWithDash()}"
}