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

package com.example.di

import com.example.data.DatabaseLoader
import com.example.server.environment.EnvironmentVar
import com.mailgun.api.v3.MailgunMessagesApi
import com.mailgun.client.MailgunClient
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {

    singleOf(::DatabaseLoader)

    single {
        val environmentVar = get<EnvironmentVar>()
        HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = environmentVar.postgresUrl
                username = environmentVar.postgresUsername
                password = environmentVar.postgresPassword
                driverClassName = "org.postgresql.Driver"
            }
        )
    }

    single<MailgunMessagesApi> {
        val environmentVar = get<EnvironmentVar>()
        MailgunClient.config(environmentVar.mailgunApi)
            .createAsyncApi( com.mailgun.api.v3.MailgunMessagesApi::class.java)
    }
}
