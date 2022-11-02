package com.example.server.route

import com.example.server.controller.AuthController
import com.example.server.controller.LoginRequest
import com.example.server.response.GenericResponse
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class AuthRoute(
    val application: Application,
    val authController: AuthController
) {
    fun configure(routing: Routing) {
        routing.route("auth") {

            post("login", {
                description = "Make login and return the session user have to operate"
                request {
                    body<LoginRequest>()
                }
                response {
                    HttpStatusCode.OK to {
                        description = "Successful Request"
                        body<GenericResponse<AuthController.LoginResponse>> { description = "the response" }
                    }
                    HttpStatusCode.InternalServerError to {
                        description = "Something unexpected happened"
                    }
                }
            }) {
                call.respond(authController.login(call.receive()))
            }

        }
    }
}