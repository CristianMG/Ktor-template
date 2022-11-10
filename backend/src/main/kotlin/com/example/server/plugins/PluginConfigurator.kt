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

import com.example.server.security.JWTSecurity
import io.ktor.server.application.*

class PluginConfigurator(
    private val jwtSecurity: JWTSecurity,
    private val httpConfiguration: HttpConfiguration,
    private val serialization: Serialization,
    private val routingConfiguration: RoutingConfiguration,
    private val swaggerUiConfiguration: SwaggerUiConfiguration,
    private val statusPageConfiguration: StatusPageConfiguration
) {
    fun configure(application: Application) {
        jwtSecurity.configure(application)
        httpConfiguration.configure(application)
        serialization.configure(application)
        routingConfiguration.configure(application)
        swaggerUiConfiguration.configure(application)
        statusPageConfiguration.configure(application)
    }
}
