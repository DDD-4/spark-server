package com.spark.poingpoing.photo

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class PhotoService(private val photoRepository: PhotoRepository) {
    fun uploadPhoto(photo: MultipartFile): String {
        return "1"
    }

    fun downloadPhoto() {
        TODO()
    }
}
