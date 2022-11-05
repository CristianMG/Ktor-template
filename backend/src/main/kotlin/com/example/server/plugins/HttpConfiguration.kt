package com.example.server.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

class HttpConfiguration(
    private val application: Application
) {
    fun configure() {
        application.apply {
            install(CORS) {
                allowMethod(HttpMethod.Options)
                allowMethod(HttpMethod.Put)
                allowMethod(HttpMethod.Delete)
                allowMethod(HttpMethod.Patch)
                allowMethod(HttpMethod.Post)
                allowHeader(HttpHeaders.Authorization)
                allowHeader(HttpHeaders.ContentType)
                anyHost()
            }
        }
    }
}
