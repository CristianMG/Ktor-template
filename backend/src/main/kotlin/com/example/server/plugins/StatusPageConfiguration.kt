package com.example.server.plugins

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
                if (internCause is ConstraintViolationException) {
                    val text = internCause.constraintViolations.joinToString(", ") { it.toMessage().message }
                    call.respond(message = ErrorResponse(text), status = HttpStatusCode.BadRequest)
                } else {
                    call.respond(message = ErrorResponse(cause.message + internCause?.message), status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }

}

