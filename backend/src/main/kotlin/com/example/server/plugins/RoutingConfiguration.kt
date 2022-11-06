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

import com.example.server.route.AuthRoute
import com.example.server.route.UserRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class RoutingConfiguration(
    private val application: Application,
    private val userRoute: UserRoute,
    private val authRoute: AuthRoute
) {
    fun configure() {
        application.routing {
            get("/") {
                call.respondText("This is the main page!!")
            }

            userRoute.configure(this)
            authRoute.configure(this)
        }
    }
}
