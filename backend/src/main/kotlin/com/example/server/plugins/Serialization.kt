package com.example.server.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

class Serialization(
    private val application: Application
) {
    fun configure() {
        application.apply {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}
