package com.hexagonal.core.exceptions

import org.springframework.http.HttpStatus

class NotFoundException(e: String, override val internalError: String) : CustomException(e, internalError) {
    override val message: String = e
    override val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
}