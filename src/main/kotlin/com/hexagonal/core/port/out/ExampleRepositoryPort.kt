package com.hexagonal.core.port.out

import com.hexagonal.core.model.ExampleCore

interface ExampleRepositoryPort {
    fun create(document: ExampleCore): ExampleCore?
}