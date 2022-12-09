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

import com.example.server.controller.AuthController
import com.example.server.route.docs.ApiSpecification
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class AuthRoute(
    private val authController: AuthController
) {

    fun configure(routing: Routing) {
        routing.route(AUTH_PATH) {

            post(LOGIN_PATH, ApiSpecification.getSpecLogin()) {
                call.respond(authController.login(call.receive()))
            }

            post(REGISTER_PATH, ApiSpecification.getSpecRegister()) {
                call.respond(authController.register(call.receive()))
            }

            authenticate("jwt") {
                get(VALIDATE_JWT_PATH, ApiSpecification.getSpecsValidateJwt()) {
                    call.respond("Your token was validated")
                }
            }
        }
    }

    companion object {
        const val AUTH_PATH = "auth"
        const val LOGIN_PATH = "login"
        const val REGISTER_PATH = "register"
        const val VALIDATE_JWT_PATH = "validateJwt"
    }
}
