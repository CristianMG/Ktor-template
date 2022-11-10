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
import com.example.server.plugins.RoutingConfiguration
import com.example.server.plugins.Serialization
import com.example.server.route.AuthRoute
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCaseOrder
import io.kotest.koin.KoinExtension
import io.kotest.koin.KoinLifecycleMode
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.network.sockets.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.ktor.server.util.*
import org.koin.dsl.module
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

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        application = TestApplication {
            application {
                routing.configure(this)
                serialization.configure(this)
                databaseLoader.connect()
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
            /*      defaultRequest {
                      url(
                          io.ktor.server.util.url {
                              protocol = URLProtocol.HTTP
                              host = "localhost"
                              port = 3000
                          }
                      )
                  }*/
        }
    }

    override fun afterSpec(f: suspend (Spec) -> Unit) {
        super.afterSpec(f)
        application.stop()
    }

    private val routing by inject<RoutingConfiguration>()
    private val serialization by inject<Serialization>()
    private val databaseLoader by inject<DatabaseLoader>()

}