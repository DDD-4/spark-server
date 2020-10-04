package com.spark.poingpoing.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [BadRequestException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun badRequest(request: HttpServletRequest, e: Exception): String? {
        log.info(formatForLog(request, e))
        return e.message

    }

    @ExceptionHandler(value = [NotFoundException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private fun notFound(request: HttpServletRequest, e: Exception): String? {
        log.info(formatForLog(request, e))
        return e.message
    }

    private fun formatForLog(request: HttpServletRequest, e:Exception) :String {
        return "url:${request.requestURI}, $e"
    }
}