package com.spark.poingpoing.util

import com.spark.poingpoing.exception.NotFoundException
import org.springframework.core.io.InputStreamResource
import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


const val BASE_DIR = "/poing_photos/"

fun findFile(fileName: String): InputStreamResource {
    val path = Paths.get(getBaseFilePath() + fileName)

    return try {
        InputStreamResource(Files.newInputStream(path))
    } catch (e: Exception) {
        throw NotFoundException("사진($path)을 찾을 수 없습니다.")
    }
}

fun getBaseFilePath(): String {
    return System.getProperty("user.home") + BASE_DIR
}

fun String.toUUIDStringWithDash(): String {
    val bigInteger1 = BigInteger(this.substring(0, 16), 16)
    val bigInteger2 = BigInteger(this.substring(16, 32), 16)
    val uuid = UUID(bigInteger1.toLong(), bigInteger2.toLong())

    return uuid.toString()
}