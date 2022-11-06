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

import com.example.server.environment.EnvironmentVar
import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.*

class SwaggerUiConfiguration(
    private val application: Application,
    private val environmentVar: EnvironmentVar
) {
    fun configure() {
        application.apply {
            install(SwaggerUI) {
                swagger {
                    swaggerUrl = environmentVar.swaggerEndpoint
                    forwardRoot = true
                }
                info {
                    title = "Swagger Documentation"
                    version = "latest"
                    description = "Example API ktor for real world with awesome tools"
                }
                server {
                    url = environmentVar.swaggerUrl
                    description = "${environmentVar.environment} Server"
                }
            }
        }
    }
}
