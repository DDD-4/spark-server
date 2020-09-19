package com.spark.poingpoing.photo

import io.swagger.annotations.Api
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@Api(tags = ["Photo"])
@RestController
class PhotoController(private val photoService: PhotoService) {

    @ApiIgnore
    @GetMapping("v1/photos/{photoId}")
    fun getPhoto(@PathVariable photoId: String): ResponseEntity<Resource> =
            photoService.downloadPhoto(photoId)
}