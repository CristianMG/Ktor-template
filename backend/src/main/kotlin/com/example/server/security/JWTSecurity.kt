package com.example.server.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.server.environment.EnvironmentVar
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

class JWTSecurity(
    private val environmentVar: EnvironmentVar,
    private val application: Application
) {
    fun configure() {
        application.apply {
            authentication {
                jwt {
                    val jwtAudience = environmentVar.jwtAudience
                    realm = environmentVar.jwtRealm
                    verifier(
                        JWT
                            .require(Algorithm.HMAC256(environmentVar.jwtSecret))
                            .withAudience(jwtAudience)
                            .withIssuer(environmentVar.jwtDomain)
                            .build()
                    )
                    validate { credential ->
                        if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
                    }
                }
            }
        }
    }
}