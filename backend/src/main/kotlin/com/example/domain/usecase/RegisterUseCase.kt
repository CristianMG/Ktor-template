package com.example.domain.usecase

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.RoleType
import com.example.data.UserRepository
import com.example.domain.exception.EmailRegisteredException
import com.example.domain.mapper.UserMapper
import com.example.domain.model.GenderModel
import com.example.domain.model.SessionResponse
import com.example.server.controller.RegisterRequest
import com.example.server.environment.EnvironmentVar
import java.time.LocalDate
import java.util.*

class RegisterUseCase(
    private val environmentVar: EnvironmentVar,
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UseCase<SessionResponse, RegisterRequest>() {

    override fun run(params: RegisterRequest): SessionResponse {
        if (userRepository.findByEmail(params.email) != null)
            throw EmailRegisteredException()

        val passwordHashed = BCrypt.withDefaults().hashToString(40, params.password.toCharArray())
        val refreshToken = BCrypt.withDefaults().hash(40, UUID.randomUUID().toString().toByteArray()).toString()

        val userSaved = userRepository.saveUser(
            params.name,
            params.lastname,
            params.email,
            params.pushToken,
            GenderModel.valueOf(params.gender),
            params.weight,
            params.height,
            LocalDate.parse(params.birthday),
            params.country,
            RoleType.USER,
            passwordHashed,
            refreshToken,
            System.currentTimeMillis() + environmentVar.refreshTokenExpirationTime
        )

        val token = JWT.create()
            .withAudience(environmentVar.jwtAudience)
            .withClaim("id", userSaved.id.toString())
            .withExpiresAt(Date(System.currentTimeMillis() + environmentVar.jwtExpirationTime))
            .sign(Algorithm.HMAC256(environmentVar.jwtSecret))

        return SessionResponse(
            token,
            refreshToken,
            userMapper.toModel(userSaved)
        )
    }
}
