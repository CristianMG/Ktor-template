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

import com.example.domain.model.GenderModel
import com.example.domain.model.SessionResponse
import com.example.response.DataResponse
import com.example.server.controller.LoginRequest
import com.example.server.controller.RegisterRequest
import io.github.serpro69.kfaker.Faker
import io.kotest.core.test.TestCaseOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.test.inject
import java.time.LocalDate

class AuthTest : BaseTest() {

    private val faker by inject<Faker>()

    init {

        describe("Auth Test suite") {
            it("Check if user can register") {
                val registerBody = RegisterRequest(
                    faker.name.name(),
                    faker.name.lastName(),
                    "admin@test.com",
                    "password_test",
                    faker.random.nextUUID(),
                    GenderModel.MAN.name,
                    faker.random.nextInt(40, 100),
                    faker.random.nextInt(100, 200),
                    LocalDate.now().toString(),
                    faker.address.countryCode()
                )

                val response = client.post("auth/register") {
                    setBody(registerBody)
                    contentType(ContentType.Application.Json)
                }
                println(response)
                response.status shouldBe HttpStatusCode.OK
                val sessionResponse = response.body<DataResponse<SessionResponse>>()
                sessionResponse shouldNotBe null
            }

            it("Check if user can make login") {
                makeLoginAndReturnResponse() shouldNotBe null
            }

        }
    }

    private suspend fun makeLoginAndReturnResponse():SessionResponse{
        val loginRequest = LoginRequest("admin@test.com","password_test")
        val response = client.post("auth/login") {
            setBody(loginRequest)
            contentType(ContentType.Application.Json)
        }
        return response.body<DataResponse<SessionResponse>>().data
    }

}