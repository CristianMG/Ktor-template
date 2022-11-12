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

package com.example.server.environment

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

class EnvironmentVar {

    val dotEnv: Dotenv by lazy {
        dotenv {
            directory = "../"
        }
    }

    val portListen: Int
        get() = getInt("PORT_LISTEN")

    val ipListen: String
        get() = getString("IP_LISTEN")

    val swaggerUrl: String
        get() = getString("SWAGGER_URL")

    val environment: String
        get() = getString("ENVIRONMENT")

    val swaggerEndpoint: String
        get() = getString("SWAGGER_ENDPOINT")

    val jwtAudience: String
        get() = getString("JWT_AUDIENCE")

    val jwtRealm: String
        get() = getString("JWT_REALM")

    val jwtSecret: String
        get() = getString("JWT_SECRET")

    val jwtExpirationTime: Int
        get() = getInt("JWT_EXPIRATION_TIME")

    val postgresUrl: String
        get() = getString("POSTGRES_URL")

    val postgresUsername: String
        get() = getString("POSTGRES_USERNAME")
    val postgresPassword: String
        get() = getString("POSTGRES_PASSWORD")

    val refreshTokenExpirationTime: Long
        get() = getLong("REFRESH_TOKEN_EXPIRATION_TIME")

    val minioUser: String
        get() = getString("MINIO_ROOT_USER")

    val minioPassword: String
        get() = getString("MINIO_ROOT_PASSWORD")

    val minioURL: String
        get() = getString("MINIO_URL")

    private fun getString(key: String): String =
        dotEnv.get(key)

    private fun getLong(key: String): Long =
        dotEnv.get(key).toLong()

    fun getInt(key: String): Int =
        dotEnv.get(key).toInt()

}
