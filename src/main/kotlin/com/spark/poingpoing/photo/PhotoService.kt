package com.spark.poingpoing.photo


import com.spark.poingpoing.util.findFile
import com.spark.poingpoing.util.getBaseFilePath
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@Service
class PhotoService(private val photoRepository: PhotoRepository) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun uploadPhoto(photo: MultipartFile): String {
        val (originalName, extension) = photo.originalFilename!!.split(".")
        val createdPhoto = photoRepository.save(Photo(name = originalName, extension = extension))

        savePhoto(createdPhoto, photo)

        return createdPhoto.pathKey.toString().replace("-", "")
    }

    private fun savePhoto(photo: Photo, photoFile: MultipartFile) {
        val savedLocation = Paths.get(getBaseFilePath() + "/${photo.pathKey}.${photo.extension}")

        log.info("savedLocation : $savedLocation")

        Files.copy(photoFile.inputStream, savedLocation, StandardCopyOption.REPLACE_EXISTING)
    }

    @Transactional(readOnly = true)
    fun downloadPhoto(photoId: String): ResponseEntity<Resource> {
        val photo = photoRepository.findFirstByPathKey(UUID.fromString(photoId))
                .orElseThrow { IllegalArgumentException("잘못된 photo 식별자 입니다.") }

        val photoStream = findFile("${photo.pathKey}.${photo.extension}")

        val photoName = "${photo.name}.${photo.extension}"
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment;filename=\"$photoName\";")
                .header("Content-Transfer-Encoding", "binary")
                .body(photoStream)
    }
}
