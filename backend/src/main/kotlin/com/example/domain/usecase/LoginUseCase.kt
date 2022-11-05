package com.example.domain.usecase

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.UserRepository
import com.example.domain.exception.LoginException
import com.example.domain.exception.UserNotFound
import com.example.domain.mapper.UserMapper
import com.example.domain.model.SessionResponse
import com.example.server.controller.LoginRequest
import com.example.server.environment.EnvironmentVar
import java.util.*

class LoginUseCase(
    private val environmentVar: EnvironmentVar,
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UseCase<SessionResponse, LoginRequest>() {

    override fun run(params: LoginRequest): SessionResponse {
        val user = userRepository.findByEmail(params.email) ?: throw UserNotFound()

        if (!BCrypt.verifyer().verify(params.password.toCharArray(), user.password).verified)
            throw LoginException()

        val token = JWT.create()
            .withAudience(environmentVar.jwtAudience)
            .withClaim("id", user.id.toString())
            .withExpiresAt(Date(System.currentTimeMillis() + environmentVar.jwtExpirationTime))
            .sign(Algorithm.HMAC256(environmentVar.jwtSecret))

        return SessionResponse(
            token,
            user.hashedRt,
            userMapper.toModel(user)
        )
    }
}
