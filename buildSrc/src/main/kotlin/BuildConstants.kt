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

object BuildConstants {

    object MainSettings {
        const val kotlinVersion = "1.9.23"
        const val ktlint = "10.3.0"
    }

    private const val ktor_version = "2.2.1"
    private const val logback_version = "1.2.11"
    private const val koin_ktor_version = "3.2.2"
    private const val dot_env_version = "6.3.1"
    //private const val swagger_ui_version = "0.7.0"
    private const val swagger_ui_version = "feature~multipart-body-SNAPSHOT"
    private const val valiktor_version = "0.7.0"
    private const val hikari_version = "5.0.1"
    private const val postgres_jdbc = "42.5.0"
    private const val exposed_version = "0.41.1"
    private const val faker_version = "1.13.0-rc.0"
    private const val slf4j_simple = "2.0.3"
    private const val bcrypt_version = "0.9.0"
    private const val fluid_country_version = "0.12.0"
    private const val mock_version = "1.13.2"
    private const val klogging = "0.5.0-SNAPSHOT"
    private const val kotest = "5.5.4"
    private const val minio = "8.4.5"
    private const val mailgunJava = "1.0.6"

    val dependencies = mutableListOf<String>().apply {
        add("io.ktor:ktor-server-core-jvm:$ktor_version")
        add("io.ktor:ktor-server-auth-jvm:$ktor_version")
        add("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
        add("io.ktor:ktor-server-sessions-jvm:$ktor_version")
        add("io.ktor:ktor-server-cors-jvm:$ktor_version")
        add("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
        add("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
        add("io.ktor:ktor-server-status-pages:${ktor_version}")
        add("io.ktor:ktor-server-request-validation:${ktor_version}")
        add("io.ktor:ktor-server-netty-jvm:$ktor_version")

        add("io.insert-koin:koin-ktor:${koin_ktor_version}")
        add("ch.qos.logback:logback-classic:$logback_version")
        add("io.github.cdimascio:dotenv-kotlin:${dot_env_version}")

        add("io.github.smiley4:ktor-swagger-ui:${swagger_ui_version}")
        add("org.valiktor:valiktor-core:${valiktor_version}")
        add("com.zaxxer:HikariCP:${hikari_version}")
        add("org.postgresql:postgresql:${postgres_jdbc}")

        add("org.jetbrains.exposed:exposed-core:$exposed_version")
        add("org.jetbrains.exposed:exposed-dao:$exposed_version")
        add("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
        add("org.jetbrains.exposed:exposed-java-time:$exposed_version")
        add("io.github.serpro69:kotlin-faker:$faker_version")
        add("org.slf4j:slf4j-simple:$slf4j_simple")
        add("at.favre.lib:bcrypt:$bcrypt_version")
        add("io.fluidsonic.country:fluid-country:$fluid_country_version")
        add("io.klogging:klogging-jvm:$klogging")
        add("io.minio:minio:${minio}")

        add("com.mailgun:mailgun-java:${mailgunJava}")

    }

    val testDependencies = mutableListOf<String>().apply {
        add("io.ktor:ktor-server-test-host:$ktor_version")
        add("io.mockk:mockk:${mock_version}")

        add("io.insert-koin:koin-test:$koin_ktor_version")
        add("io.kotest:kotest-runner-junit5:$kotest")
        add("io.kotest:kotest-assertions-core:$kotest")
        add("io.kotest.extensions:kotest-extensions-koin:1.1.0")
        add("io.ktor:ktor-client-content-negotiation:$ktor_version")
        add("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
        add("io.ktor:ktor-client-logging:$ktor_version")
    }

    object Classpath {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${MainSettings.kotlinVersion}"
        const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:12.1.0"
    }

}
