package com.spark.poingpoing.user

import com.spark.poingpoing.user.auth.LoginResponse
import com.spark.poingpoing.user.auth.JwtTokenProvider
import com.spark.poingpoing.util.convertToPhotoUrl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Api(tags = ["User"])
@RestController
class UserController(
        private val loginUserGetter: LoginUserGetter,
        private val userService: UserService,
        private val jwtTokenProvider: JwtTokenProvider) {

    @ApiOperation("회원 가입")
    @PostMapping("v1/users/sign-up")
    fun createUser(userCreateRequest: UserCreateRequest): UserResponse {
        return userService.createUser(userCreateRequest)
    }

    @ApiOperation("로그인")
    @PostMapping("v1/users/login")
    fun login(@RequestBody loginRequest: LoginRequest, httpServletResponse: HttpServletResponse): LoginResponse {
        val user = userService.findUser(loginRequest)
        val token = jwtTokenProvider.createToken(user)

        return  LoginResponse(
                token,
                UserResponse(user.id, user.name, user.email, user.photoPath.convertToPhotoUrl())
        )
    }

    @ApiOperation("내정보 조회")
    @GetMapping("v1/users")
    fun findUser(httpServletRequest: HttpServletRequest): UserResponse {
        val user = loginUserGetter.getLoginUser(httpServletRequest)

        return UserResponse(user.id, user.name, user.email, user.photoPath.convertToPhotoUrl())
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