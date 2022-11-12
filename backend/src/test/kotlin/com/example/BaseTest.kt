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

package com.example

import com.example.data.DatabaseLoader
import com.example.data.seed.seedModule
import com.example.di.*
import com.example.server.plugins.PluginConfigurator
import com.example.server.security.JWTSecurity
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCaseOrder
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import org.koin.test.KoinTest
import org.koin.test.inject

open class BaseTest : DescribeSpec(), KoinTest {

    lateinit var application: TestApplication
    lateinit var client: HttpClient

    override fun testCaseOrder(): TestCaseOrder? = TestCaseOrder.Sequential

    override fun extensions(): List<Extension> {
        return listOf(
            KoinExtension(
                modules = listOf(
                    configurationModule, routeModule, controllerModule,
                    useCasesModule, repositoryModule, databaseModule, seedModule, mapperModule, environmentModule
                ), mode = KoinLifecycleMode.Root
            )
        )
    }

    open  fun databaseLoaded(){}

    override suspend fun beforeSpec(spec: Spec) {
        application = TestApplication {
            application {
                pluginConfigurator.configure(this)
                databaseLoader.connect()
                databaseLoaded()
            }
        }
        client = application.client.config {
            install(ContentNegotiation) {
                json()
            }
            Logging {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
        }
    }

    override fun afterSpec(f: suspend (Spec) -> Unit) {
        super.afterSpec(f)
        application.stop()
    }

    private val databaseLoader by inject<DatabaseLoader>()
    private val pluginConfigurator: PluginConfigurator by inject()
    private val auth by inject<JWTSecurity>()

}