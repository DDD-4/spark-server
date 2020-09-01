package com.spark.poingpoing.photo

import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths

@RestController
class PhotoController {

    @GetMapping("v1/photos/{photoKey}")
    fun getPhoto(@PathVariable photoKey: String): ResponseEntity<Resource> {

        val path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/$photoKey")

        val photo = try {
            InputStreamResource(Files.newInputStream(path))
        } catch (e: Exception) {
            throw IllegalArgumentException("존재하지 않는 사진 입니다.")
        }

        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(photo)
    }
}