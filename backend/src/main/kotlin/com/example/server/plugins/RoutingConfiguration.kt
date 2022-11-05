package com.example.server.plugins

import com.example.server.route.AuthRoute
import com.example.server.route.UserRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class RoutingConfiguration(
    private val application: Application,
    private val userRoute: UserRoute,
    private val authRoute: AuthRoute
) {
    fun configure() {
        application.routing {
            get("/") {
                call.respondText("This is the main page!!")
            }

            userRoute.configure(this)
            authRoute.configure(this)
        }
    }
}
