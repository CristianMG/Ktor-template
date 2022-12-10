/*
 * Copyright (c) 2022 CristianMg <https://github.com/CristianMG>
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.server.plugins

import com.example.domain.exception.EmailRegisteredException
import com.example.domain.exception.LoginException
import com.example.server.dto.ErrorResponseDTO
import com.example.server.dto.wrapResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.toMessage

class PluginStatusPageConfiguration() {
    fun configure(application: Application) = with(application) {
        install(StatusPages) {
            exception<Throwable> { call, cause ->
                val internCause = cause.cause
                when {
                    internCause is ConstraintViolationException -> {
                        val errors = internCause.constraintViolations.map { it.toMessage() }
                        call.respond(HttpStatusCode.BadRequest, wrapResponse { ErrorResponseDTO(errors.toString()) })
                    }

                    cause is LoginException -> {
                        call.respond(HttpStatusCode.Unauthorized, wrapResponse { ErrorResponseDTO("Invalid credentials") })
                    }

                    cause is EmailRegisteredException -> {
                        call.respond(HttpStatusCode.Conflict, wrapResponse { ErrorResponseDTO("Email already registered") })
                    }

                    cause is BadRequestException || internCause is BadRequestException -> {
                        call.respond(HttpStatusCode.BadRequest, wrapResponse { ErrorResponseDTO(cause.message ?: "") })
                    }

                    else -> {
                        call.respond(HttpStatusCode.InternalServerError, wrapResponse { ErrorResponseDTO("Internal server error") })
                        println("Internal server error: exception: $cause internCause: ${internCause?.message} outputCase: ${cause.message}")
                    }
                }
            }
        }
    }
}
