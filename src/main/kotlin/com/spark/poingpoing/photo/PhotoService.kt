package com.spark.poingpoing.photo


import com.spark.poingpoing.util.findFile
import com.spark.poingpoing.util.toUUIDStringWithDash
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class PhotoService(private val photoRepository: PhotoRepository) {

    fun uploadPhoto(photo: MultipartFile): String {
        return "b2f6e721-aeef-478f-b81e-942c705071bc"
    }

    fun downloadPhoto(photoId: String): ResponseEntity<Resource> {
        val uuidKey = UUID.fromString(photoId.toUUIDStringWithDash())
        val (id, name, extension) = photoRepository.findById(uuidKey)
                .orElseThrow { IllegalArgumentException("잘못된 photo 식별자 입니다.") }

        val photoStream = findFile("$id.$extension")

        val photoName = "$name.$extension"
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment;filename=\"$photoName\";")
                .header("Content-Transfer-Encoding", "binary")
                .body(photoStream)
    }
}
