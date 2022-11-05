package com.example.server.plugins

import com.example.server.controller.LoginRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

class ValidatorConfiguration(
    private val application: Application
) {
    fun configure() {
        application.install(RequestValidation) {
            validate<LoginRequest> {
                ValidationResult.Valid
            }
        }
    }
}
