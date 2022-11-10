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

import com.example.data.Users
import com.example.domain.model.GenderModel
import com.example.domain.model.SessionResponse
import com.example.response.DataResponse
import com.example.server.controller.LoginRequest
import com.example.server.controller.RegisterRequest
import com.example.server.route.AuthRoute.Companion.AUTH_PATH
import com.example.server.route.AuthRoute.Companion.LOGIN_PATH
import com.example.server.route.AuthRoute.Companion.REGISTER_PATH
import com.example.server.route.AuthRoute.Companion.VALIDATE_JWT_PATH
import io.github.serpro69.kfaker.Faker
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.test.inject
import java.time.LocalDate


class AuthTest : BaseTest() {

    companion object {
        const val EMAIL = "test@test.com"
        const val PASSWORD = "password_test"

    }


    private val faker by inject<Faker>()

    override fun databaseLoaded() {
        transaction {
            Users.deleteAll()
        }
    }

    val registerBody:RegisterRequest by lazy {
        RegisterRequest(
            faker.name.name(),
            faker.name.lastName(),
            EMAIL,
            PASSWORD,
            faker.random.nextUUID(),
            GenderModel.MAN.name,
            faker.random.nextInt(40, 100),
            faker.random.nextInt(100, 200),
            LocalDate.now().toString(),
            faker.address.countryCode()
        )
    }

    init {
        describe("Auth Test suite") {

            it("Check if user can register") {
                val response = client.post("$AUTH_PATH/$REGISTER_PATH") {
                    setBody(registerBody)
                    contentType(ContentType.Application.Json)
                }
                println(response)
                response.status shouldBe HttpStatusCode.OK
                val sessionResponse = response.body<DataResponse<SessionResponse>>()
                sessionResponse shouldNotBe null
            }

            it("Check user was already registered") {
                val response = client.post("$AUTH_PATH/$REGISTER_PATH") {
                    setBody(registerBody)
                    contentType(ContentType.Application.Json)
                }
                response.status shouldBe HttpStatusCode.Conflict
            }

            it("Check if user can make login") {
                val session = makeLoginAndReturnResponse()
                session shouldNotBe null
                session.token shouldNotBe null
                session.refreshToken shouldNotBe null
                session.user shouldNotBe null

                val result = client.get("$AUTH_PATH/$VALIDATE_JWT_PATH") {
                    headers {
                        append("Authorization", "Bearer ${session.token}")
                    }
                }

                result shouldNotBe null
                result.status shouldBe HttpStatusCode.OK
                result.bodyAsText() shouldBe "Your token was validated"
            }

            it("Check Unauthorized user") {
                val result = client.get("$AUTH_PATH/$VALIDATE_JWT_PATH") {
                    headers {
                        append("Authorization", "Bearer dasdasdasdasd")
                    }
                }

                result shouldNotBe null
                result.status shouldBe HttpStatusCode.Unauthorized
            }
        }
    }


    private suspend fun makeLoginAndReturnResponse(): SessionResponse {
        val loginRequest = LoginRequest(
            EMAIL, PASSWORD
        )
        val response = client.post("$AUTH_PATH/$LOGIN_PATH") {
            setBody(loginRequest)
            contentType(ContentType.Application.Json)
        }
        return response.body<DataResponse<SessionResponse>>().data
    }

}