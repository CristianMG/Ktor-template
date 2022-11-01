package com.example.domain.usecase

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.server.environment.EnvironmentVar
import java.util.*

class LoginUseCase(
    private val environmentVar: EnvironmentVar
) : UseCase<String, LoginParam>() {

    override fun run(params: LoginParam): String {
        //TODO first checking in database
        val token = JWT.create()
            .withAudience(environmentVar.jwtAudience)
            .withClaim("username", "ender de palo")
            .withExpiresAt(Date(System.currentTimeMillis() + environmentVar.jwtExpirationTime))
            .sign(Algorithm.HMAC256(environmentVar.jwtSecret))

        return token
    }

}

data class LoginParam(
    val email: String, val password: String
)