package com.spark.poingpoing.user

import com.spark.poingpoing.user.auth.AuthService
import com.spark.poingpoing.util.convertToPhotoUrl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@Api(tags = ["User"])
@RestController
class UserController(
        private val loginUserGetter: LoginUserGetter,
        private val userService: UserService,
        private val authService: AuthService) {

    @ApiOperation("회원 가입")
    @PostMapping("v1/users")
    fun createUser(userCreateRequest: UserCreateRequest) {
        userService.createUser(userCreateRequest)
    }

    @ApiOperation("로그인")
    @GetMapping("v1/users/login")
    fun login(@RequestBody loginRequest: LoginRequest) {
        val token = authService.createToken(loginRequest)
        //todo
    }

    @ApiOperation("로그아웃")
    @GetMapping("v1/users/logout")
    fun logout() {
        //todo
    }

    @ApiOperation("내정보 조회")
    @GetMapping("v1/users")
    fun findUser(httpServletRequest: HttpServletRequest): UserResponse {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        return UserResponse(user.id, user.name, user.photoPath.convertToPhotoUrl())
    }

    @ApiOperation("프로필 수정")
    @PatchMapping("v1/users")
    fun modifyProfile(httpServletRequest: HttpServletRequest,
                      @ModelAttribute @RequestBody userUpdateRequest: UserUpdateRequest) {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        userService.modifyUser(user, userUpdateRequest)
    }

    @ApiOperation("회원탈퇴")
    @DeleteMapping("v1/users")
    fun deleteUser(httpServletRequest: HttpServletRequest) {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        userService.deleteUser(user)
    }
}