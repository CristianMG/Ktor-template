package com.example.server.plugins

import com.example.domain.exception.EmailRegisteredException
import com.example.server.response.ErrorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.toMessage

class StatusPageConfiguration(
    private val application: Application
) {
    fun configure() {
        application.install(StatusPages) {
            exception<Throwable> { call, cause ->
                val internCause = cause.cause

                when (internCause) {
                    is ConstraintViolationException -> {
                        val errors = internCause.constraintViolations.map { it.toMessage() }
                        call.respond(HttpStatusCode.BadRequest, ErrorResponse(errors.toString()))
                    }
                    is EmailRegisteredException -> {
                        call.respond(HttpStatusCode.Conflict, ErrorResponse("Email already registered"))
                    }
                    else -> {
                        call.respond(HttpStatusCode.InternalServerError, ErrorResponse("Internal server error"))
                    }
                }
            }
        }
    }
}
