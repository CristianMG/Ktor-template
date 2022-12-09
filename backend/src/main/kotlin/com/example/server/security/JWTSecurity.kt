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

package com.example.server.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.repository.UserRepository
import com.example.server.environment.EnvironmentVar
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

class JWTSecurity(
    private val environmentVar: EnvironmentVar,
    private val userRepository: UserRepository
) {
    fun configure(application: Application) {
        application.apply {
            install(Authentication) {
                jwt("jwt") {
                    val jwtAudience = environmentVar.jwtAudience
                    realm = environmentVar.jwtRealm
                    verifier(
                        JWT
                            .require(Algorithm.HMAC256(environmentVar.jwtSecret))
                            .withAudience(jwtAudience)
                            .build()
                    )
                    validate { credential ->
                        val id = credential.payload.getClaim("id").asString()
                        println("id: $id")
                        if (credential.payload.audience.contains(jwtAudience))
                            userRepository.findById(id)
                        else
                            null
                    }
                }
            }
        }
    }
}
