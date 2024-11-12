package com.hexagonal.core.enums

enum class ErrorEnum(val code: String, val message: String) {
    HX0001("HX-0001", "Invalid payload"),
    HX1001("HX-1001", "Client not found"),
    HX1002("HX-2001", "Client wrong password"),

}