package com.aeon.api.module.customerregistration.models.error

import com.aeon.api.module.customerregistration.models.dtos.ResponseApiDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class CustomGlobalExceptionHandler(): ResponseEntityExceptionHandler() {

    fun customHandleNotFound(message: String): ResponseApiDTO  =
        ResponseApiDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.toString(),
            null,
            message)

    fun customHandleBadRequest(message: String): ResponseApiDTO  =
        ResponseApiDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.toString(),
            null,
            message)

    fun customHandleForbidden(message: String): ResponseApiDTO  =
        ResponseApiDTO(
            LocalDateTime.now(),
            HttpStatus.FORBIDDEN.toString(),
            null,
            message)
}