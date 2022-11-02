package com.example.server.controller

import kotlinx.serialization.Serializable
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import kotlin.jvm.Throws

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
) {
    init {
        validate(this) {
            validate(LoginRequest::email).isEmail()
            validate(LoginRequest::password).isNotBlank().hasSize(min = 8)
        }
    }
}