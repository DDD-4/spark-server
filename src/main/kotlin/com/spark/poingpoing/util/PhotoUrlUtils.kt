package com.spark.poingpoing.util

private const val PHOTO_SERVER_HOST = "http://www.poingpoing.cf/v1/photos"

fun String.convertToPhotoUrl(): String {
    return "$PHOTO_SERVER_HOST/${this.toUUIDStringWithDash()}"
}