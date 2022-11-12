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

package com.example.domain.usecase

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.RoleType
import com.example.data.UserRepository
import com.example.domain.exception.EmailRegisteredException
import com.example.domain.model.GenderModel
import com.example.domain.model.SessionModel
import com.example.server.dto.request.RegisterRequestDTO
import com.example.server.environment.EnvironmentVar
import java.time.LocalDate
import java.util.*

class RegisterUseCase(
    private val environmentVar: EnvironmentVar,
    private val userRepository: UserRepository
) {


    operator fun invoke(params: RegisterUseCaseParam): SessionModel {
        if (userRepository.findByEmail(params.request.email) != null)
            throw EmailRegisteredException()

        val passwordHashed = BCrypt.withDefaults().hashToString(10, params.request.password.toCharArray())
        val refreshToken = BCrypt.withDefaults().hashToString(10, UUID.randomUUID().toString().toCharArray())

        val userSaved = userRepository.saveUser(
            params.request.name,
            params.request.lastname,
            params.request.email,
            params.request.pushToken,
            GenderModel.valueOf(params.request.gender),
            params.request.weight,
            params.request.height,
            LocalDate.parse(params.request.birthday),
            params.request.country,
            params.roleType,
            passwordHashed,
            refreshToken,
            System.currentTimeMillis() + environmentVar.refreshTokenExpirationTime
        ).toModel()

        val token = JWT.create()
            .withAudience(environmentVar.jwtAudience)
            .withClaim("id", userSaved.id.toString())
            .withExpiresAt(Date(System.currentTimeMillis() + environmentVar.jwtExpirationTime))
            .sign(Algorithm.HMAC256(environmentVar.jwtSecret))

        return SessionModel(
            token,
            refreshToken,
            userSaved
        )
    }

    data class RegisterUseCaseParam(
        val request: RegisterRequestDTO,
        val roleType: RoleType
    )
}
