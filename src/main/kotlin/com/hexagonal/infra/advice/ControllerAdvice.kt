package com.hexagonal.infra.advice

import com.hexagonal.core.enums.ErrorEnum
import com.hexagonal.core.exceptions.CustomException
import com.hexagonal.core.exceptions.NotFoundException
import com.hexagonal.entrypoint.http.dto.response.ErrorResponse
import com.hexagonal.entrypoint.http.dto.response.FieldError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleSpringValidatorException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return  ResponseEntity(ErrorResponse(
            httpCode = HttpStatus.OK.value(),
            message = ErrorEnum.HX0001.message,
            internalCode = ErrorEnum.HX0001.code,
            errors = e.bindingResult.fieldErrors.map { FieldError(
                it.field,
                it.defaultMessage ?: "Invalid value"
            ) }
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: CustomException): ResponseEntity<ErrorResponse>{
        return ResponseEntity(
            ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = e.message?: "Data not found",
            internalCode = e.internalError?: "0000",
            errors = null
        ),HttpStatus.NOT_FOUND)
    }
}