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

package com.example.server

import com.example.data.DatabaseLoader
import com.example.data.seed.seedModule
import com.example.di.*
import com.example.server.environment.EnvironmentVar
import com.example.server.plugins.*
import com.example.server.security.JWTSecurity
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {

    embeddedServer(Netty, environment = applicationEngineEnvironment {
        module {
            install(Koin) {
                modules(
                    configurationModule, routeModule, controllerModule, useCasesModule, repositoryModule, databaseModule, seedModule, environmentModule, mapperDTOModule
                )
            }

            val env: EnvironmentVar by inject()
            val pluginConfigurator: PluginConfigurator by inject()
            val loader: DatabaseLoader by inject()

            pluginConfigurator.configure(this)
            loader.connect()

            connector {
                port = env.portListen
                host = env.ipListen
            }
        }
    }).start(wait = true)
}
