package com.spark.poingpoing.user

import com.spark.poingpoing.exception.BadRequestException
import com.spark.poingpoing.exception.ForbiddenException
import com.spark.poingpoing.exception.NotFoundException
import com.spark.poingpoing.folder.FolderService
import com.spark.poingpoing.photo.PhotoService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
        private val userRepository: UserRepository,
        private val photoService: PhotoService,
        private val folderService: FolderService,
        private val passwordEncoder: PasswordEncoder) {

    @Transactional
    fun createUser(userCreateRequest: UserCreateRequest): UserResponse {
        val history = userRepository.findFirstByCredentialAndActiveIsTrue(userCreateRequest.credential)
        if(history.isPresent) {
            throw BadRequestException("이미 존재하는 사용자 입니다.")
        }

        val user = User(
                name = userCreateRequest.name,
                credential = userCreateRequest.credential
        )

        folderService.createDefaultFolder(user)

        return UserResponse.of(userRepository.save(user))
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

    @Transactional(readOnly = true)
    fun findUser(loginRequest: LoginRequest): User {
        val user = userRepository.findFirstByCredentialAndActiveIsTrue(loginRequest.credential)
                .orElseThrow {
                    NotFoundException("일치하는 사용자가 없습니다.")
                }

        println(passwordEncoder.encode(loginRequest.credential))

        if (loginRequest.credential == user.credential) {
            return user
        }

        throw ForbiddenException("인증키가 틀렸습니다")
    }
}
