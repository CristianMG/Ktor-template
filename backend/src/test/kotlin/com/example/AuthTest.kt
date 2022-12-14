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

import com.example.data.entity.Multimedia
import com.example.data.entity.TempAuth
import com.example.data.entity.Users
import com.example.data.repository.MailRepository
import com.example.domain.model.GenderModel
import com.example.domain.model.TempAuthModel
import com.example.response.DataResponse
import com.example.server.dto.request.RegisterRequestDTO
import com.example.server.dto.response.SessionResponseDTO
import com.example.server.dto.response.UserResponseDTO
import com.example.server.route.AuthRoute.Companion.AUTH_PATH
import com.example.server.route.AuthRoute.Companion.REGISTER_PATH
import com.example.server.route.AuthRoute.Companion.VALIDATE_JWT_PATH
import com.example.server.route.UserRoute
import io.github.serpro69.kfaker.Faker
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.test.inject
import org.koin.test.mock.MockProvider
import org.koin.test.mock.declareMock
import java.time.LocalDate

class AuthTest : BaseTest() {

    private val faker by inject<Faker>()

    override fun databaseLoaded() {
        transaction {
            TempAuth.deleteAll()
            Multimedia.deleteAll()
            Users.deleteAll()
        }
    }

    private val registerBody: RegisterRequestDTO by lazy {
        RegisterRequestDTO(
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
                MockProvider.register { mockkClass(it) }

                declareMock<MailRepository> {
                    every { sendMessageConfirmMail(any()) } coAnswers {
                        val response = client.get("${UserRoute.USER_PATH}/${UserRoute.CONFIRM_EMAIL}") {
                            contentType(ContentType.Application.Any)
                            parameter("token", firstArg<TempAuthModel>().id)
                        }
                        response.body<DataResponse<UserResponseDTO>>().apply {
                            this.data shouldNotBe null
                            this.data.emailValidated shouldBe true
                        }
                        mockk()
                    }
                }

                val response = client.post("$AUTH_PATH/$REGISTER_PATH") {
                    setBody(registerBody)
                    contentType(ContentType.Application.Json)
                }
                response.status shouldBe HttpStatusCode.OK
                response.body<DataResponse<SessionResponseDTO>>().apply {
                    this shouldNotBe null
                }
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
}
