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

import com.example.data.UserEntity
import com.example.server.route.docs.ApiSpecification
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.nio.file.Files
import kotlin.io.path.writeBytes

class UserRoute() {

    fun configure(routing: Routing) {
        routing.authenticate("jwt") {
            routing.route("user") {
                get(
                    "me", ApiSpecification.getSpecGetUserMe()
                ) {
                    val context = call.principal<UserEntity>()
                    call.respond("Ender!! ${context?.id}")
                }
            }
            post(
                "updateMyImage", ApiSpecification.updateMyImage()
            ) {
                val multipartData = call.receiveMultipart()
                val data = multipartData.readPart() ?: throw BadRequestException("No file")
                if (data is PartData.FileItem) {
                    val file = data.streamProvider().readBytes()
                    val path = Files.createTempFile(null, null)
                    path.writeBytes(file)
                    call.respond("Ender!! $path")
                }
                // call.respond("Ender!! ${context?.id}")
            }
        }
    }
}
