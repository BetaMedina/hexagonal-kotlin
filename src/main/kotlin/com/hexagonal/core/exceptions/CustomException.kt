package com.hexagonal.core.exceptions

import org.springframework.http.HttpStatus

open class CustomException(e: String, internalError: String): Exception() {
    open val httpStatus: HttpStatus?= null
    open val internalError: String ?= null
    override val message: String?= null
}