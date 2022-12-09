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

import com.example.response.DataResponse
import com.example.server.dto.response.UserResponseDTO
import com.example.server.route.UserRoute.Companion.UPDATE_MY_IMAGE_PATH
import com.example.server.route.UserRoute.Companion.USER_ME_PATH
import com.example.server.route.UserRoute.Companion.USER_PATH
import com.example.utils.addFile
import com.example.utils.checkImage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import java.io.File

class UserTest : BaseTest() {

    init {
        describe("Checking User paths") {

            it("Check if user can request their own user") {
                makeLoginAndReturnResponse().let { session ->
                    val userResponse = client.get("$USER_PATH/$USER_ME_PATH") {
                        headers {
                            append("Authorization", "Bearer ${session.token}")
                        }
                    }
                    userResponse.status shouldBe HttpStatusCode.OK
                    val response = userResponse.body<DataResponse<UserResponseDTO>>()
                    response.data shouldNotBe null
                    response.data.id shouldBe session.user.id
                }
            }

            it("Check if users can update their own profile image") {
                val file = File(javaClass.getResource("/image_example.jpg")?.file!!)
                makeLoginAndReturnResponse().let {
                    client.post(
                        "$USER_PATH/$UPDATE_MY_IMAGE_PATH"
                    ) {
                        headers {
                            append("Authorization", "Bearer ${it.token}")
                        }
                        setBody(
                            MultiPartFormDataContent(formData { addFile("image", file) })
                        )
                    }
                }.let { response ->
                    response.status shouldBe HttpStatusCode.OK
                    response.body<DataResponse<UserResponseDTO>>().apply {
                        data shouldNotBe null
                        data.profilePicture shouldNotBe null
                        minioClient.checkImage(data.profilePicture!!)
                    }
                }
            }
        }
    }
}
