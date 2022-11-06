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
        get() = dotEnv.get("PORT_LISTEN").toInt()

    val ipListen: String
        get() = dotEnv.get("IP_LISTEN")

    val swaggerUrl: String
        get() = dotEnv.get("SWAGGER_URL")

    val environment: String
        get() = dotEnv.get("ENVIRONMENT")

    val swaggerEndpoint: String
        get() = dotEnv.get("SWAGGER_ENDPOINT")

    val jwtAudience: String
        get() = dotEnv.get("JWT_AUDIENCE")

    val jwtRealm: String
        get() = dotEnv.get("JWT_REALM")

    val jwtSecret: String
        get() = dotEnv.get("JWT_SECRET")

    val jwtDomain: String
        get() = dotEnv.get("JWT_DOMAIN")

    val jwtExpirationTime: Int
        get() = dotEnv.get("JWT_EXPIRATION_TIME").toInt()

    val postgresUrl: String
        get() = dotEnv.get("POSTGRES_URL")

    val postgresUsername: String
        get() = dotEnv.get("POSTGRES_USERNAME")
    val postgresPassword: String
        get() = dotEnv.get("POSTGRES_PASSWORD")

    val refreshTokenExpirationTime: Long
        get() = dotEnv.get("REFRESH_TOKEN_EXPIRATION_TIME").toLong()
}
