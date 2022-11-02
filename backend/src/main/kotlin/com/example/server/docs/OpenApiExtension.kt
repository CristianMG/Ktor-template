package com.example.server.docs

import io.github.smiley4.ktorswaggerui.dsl.OpenApiResponse
import io.github.smiley4.ktorswaggerui.dsl.OpenApiRoute
import io.ktor.http.*


fun OpenApiRoute.responseGeneric(block: OpenApiResponse.() -> Unit) {
    response {
        HttpStatusCode.OK to {
            description = "Successful Request"
            block()
        }
        HttpStatusCode.InternalServerError to {
            description = "Something unexpected happened"
        }
    }
}