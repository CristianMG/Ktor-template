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

package com.example.server.route

import com.example.domain.model.SessionResponse
import com.example.server.controller.AuthController
import com.example.server.controller.LoginRequest
import com.example.server.controller.RegisterRequest
import com.example.server.docs.responseGeneric
import com.example.server.response.GenericResponse
import io.github.smiley4.ktorswaggerui.dsl.post
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
                responseGeneric { body<GenericResponse<SessionResponse>>() }
            }) {
                call.respond(authController.login(call.receive()))
            }

            post("register", {
                description = "Make a register in the platform"
                request {
                    body<RegisterRequest>()
                }
                responseGeneric { body<GenericResponse<SessionResponse>>() }
            }) {
                call.respond(authController.login(call.receive()))
            }
        }
    }
}
