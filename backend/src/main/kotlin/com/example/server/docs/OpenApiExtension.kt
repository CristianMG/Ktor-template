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

package com.example.server.docs

import io.github.smiley4.ktorswaggerui.dsl.OpenApiResponse
import io.github.smiley4.ktorswaggerui.dsl.OpenApiResponses
import io.github.smiley4.ktorswaggerui.dsl.OpenApiRoute
import io.ktor.http.*

fun OpenApiRoute.responseGeneric(error: (OpenApiResponses.() -> Unit)? = null, responseOk: OpenApiResponse.() -> Unit) {
    response {
        HttpStatusCode.OK to {
            description = "Successful Request"
            responseOk()
        }
        HttpStatusCode.InternalServerError to {
            description = "Something unexpected happened"
        }
        HttpStatusCode.Unauthorized to {
            description = "Your credentials are wrong"
        }

        error?.invoke(this)
    }
}
