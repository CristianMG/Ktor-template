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

package com.example.server.route.docs

import com.example.domain.model.SessionResponse
import com.example.server.controller.LoginRequest
import com.example.server.controller.RegisterRequest
import com.example.server.docs.responseGeneric
import com.example.server.response.GenericResponse
import io.github.smiley4.ktorswaggerui.dsl.OpenApiRoute
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import io.ktor.http.content.*
import java.io.File


enum class TAGS(val value: String) {
    AUTH("AUTH"),
    USER("USER"),
}

object ApiSpecification {

    fun getSpecRegister(): OpenApiRoute.() -> Unit = {
        tags = listOf(TAGS.AUTH.value)
        description = "Make a register in the platform"
        request {
            body<RegisterRequest>()
        }
        responseGeneric(
            {
                HttpStatusCode.BadRequest to {
                    description = "The request is not valid"
                }
                HttpStatusCode.Unauthorized to {
                    description = "Your login is wrong"
                }
                HttpStatusCode.Conflict to {
                    description = "The email is already registered, please use other email to register"
                }
            }
        ) { body<GenericResponse<SessionResponse>>() }
    }

    fun getSpecLogin(): OpenApiRoute.() -> Unit = {
        tags = listOf(TAGS.AUTH.value)
        description = "Make login and return the session user have to operate"
        request {
            body<LoginRequest>()
        }
        responseGeneric(
            {
                HttpStatusCode.BadRequest to {
                    description = "The request is not valid"
                }
            }
        ) { body<GenericResponse<SessionResponse>>() }
    }
    fun getSpecsValidateJwt(): OpenApiRoute.() -> Unit = {
        tags = listOf(TAGS.AUTH.value)

        responseGeneric({
        }){
            body<String>()
        }
    }


    fun getSpecGetUserMe(): OpenApiRoute.() -> Unit = {
        tags = listOf(TAGS.USER.value)
    }

    fun updateMyImage(): OpenApiRoute.() -> Unit = {
        tags = listOf(TAGS.USER.value)
        request {
            body{
                mediaType(ContentType.Image.Any)
                required = true
                description = "The file to upload"


            }
        }

    }

}