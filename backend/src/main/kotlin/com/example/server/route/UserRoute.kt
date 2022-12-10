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

package com.example.server.route

import com.example.domain.model.UserModel
import com.example.server.controller.UserController
import com.example.server.dto.mapper.UserMapperDTO
import com.example.server.dto.wrapResponse
import com.example.server.route.docs.ApiSpecification
import com.example.server.util.getAsTempFile
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class UserRoute(
    private val controller: UserController,
    private val userMapper: UserMapperDTO
) {

    fun configure(routing: Routing) = with(routing) {
        route(USER_PATH) {
            get(CONFIRM_EMAIL, ApiSpecification.confirmEmail()) {
                call.parameters["token"]?.let {
                    call.respond(controller.confirmEmail(it))
                } ?: throw BadRequestException("No token")
            }
        }
        authenticate("jwt") {
            route(USER_PATH) {
                get(
                    USER_ME_PATH, ApiSpecification.getSpecGetUserMe()
                ) {
                    call.respond(wrapResponse { userMapper.toDto(call.principal()!!) })
                }

                post(
                    UPDATE_MY_IMAGE_PATH, ApiSpecification.updateMyImage()
                ) {
                    val user = call.principal<UserModel>()!!
                    val multipartData = call.receiveMultipart()
                    val data = multipartData.readPart() ?: throw BadRequestException("No file")
                    if (data.name == "image" &&
                        data is PartData.FileItem
                    ) {
                        val file = data.getAsTempFile()
                        call.respond(controller.updateImage(file, user.id))
                    } else {
                        throw BadRequestException("No file or bad name")
                    }
                }
            }
        }

    }

    companion object {
        const val USER_PATH = "user"
        const val USER_ME_PATH = "me"
        const val UPDATE_MY_IMAGE_PATH = "updateMyImage"
        const val CONFIRM_EMAIL = "confirm-email"
    }
}
