package com.spark.poingpoing.user

import com.spark.poingpoing.photo.PhotoService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
        private val userRepository: UserRepository,
        private val photoService: PhotoService) {

    @Transactional
    fun createUser(userCreateRequest: UserCreateRequest) {
        val user = User(
                name = userCreateRequest.name,
                credential = userCreateRequest.credential
        )

        userRepository.save(user)
    }

    @Transactional
    fun modifyUser(user: User, userUpdateRequest: UserUpdateRequest) {
        userUpdateRequest.name?.let { user.modifyName(it) }
        userUpdateRequest.photo?.let {
            val newPhotoPath = photoService.uploadPhoto(it)
            user.photoPath = newPhotoPath
        }

        userRepository.save(user)
    }

    @Transactional
    fun deleteUser(user: User) {
        user.delete()

        userRepository.save(user)
    }
}
