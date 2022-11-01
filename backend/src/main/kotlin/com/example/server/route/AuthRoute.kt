package com.example.server.route

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.server.environment.EnvironmentVar
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.server.response.*
import java.util.*

class AuthRoute(
    val application: Application,
    val environmentVar: EnvironmentVar,
) {
    fun configure(routing: Routing) {
        routing.route("auth") {

            post("login", {
                description = "Make login and return the session user have to operate"
            }) {
                val token = JWT.create()
                    .withAudience(environmentVar.jwtAudience)
                    .withClaim("username", "ender de palo")
                    .withExpiresAt(Date(System.currentTimeMillis() + environmentVar.jwtExpirationTime))
                    .sign(Algorithm.HMAC256(environmentVar.jwtSecret))

                call.respond(token)
            }
        }
    }
}