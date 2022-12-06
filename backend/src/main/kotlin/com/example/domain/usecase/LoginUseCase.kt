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
import com.example.data.repository.UserRepository
import com.example.domain.exception.LoginException
import com.example.domain.model.SessionModel
import com.example.server.environment.EnvironmentVar
import java.util.*

class LoginUseCase(
    private val environmentVar: EnvironmentVar,
    private val userRepository: UserRepository
) {

    operator fun invoke(
         email: String,
         password: String
    ): SessionModel {
        val user = userRepository.findByEmail(email) ?: throw LoginException()

        if (!BCrypt.verifyer().verify(password.toCharArray(), user.password).verified)
            throw LoginException()

        val token = JWT.create()
            .withAudience(environmentVar.jwtAudience)
            .withClaim("id", user.id.toString())
            .withExpiresAt(Date(System.currentTimeMillis() + environmentVar.jwtExpirationTime))
            .sign(Algorithm.HMAC256(environmentVar.jwtSecret))

        return SessionModel(
            token,
            user.refreshToken,
            user
        )
    }
}
