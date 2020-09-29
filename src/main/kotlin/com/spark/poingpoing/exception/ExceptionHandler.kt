package com.spark.poingpoing.exception

import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = [IllegalArgumentException::class, NotFoundException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun badRequest(request: HttpServletRequest, e: Exception): String? {
        return e.message
    }
}