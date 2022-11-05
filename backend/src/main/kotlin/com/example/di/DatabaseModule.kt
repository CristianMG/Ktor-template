package com.example.di

import com.example.data.DatabaseLoader
import com.example.server.environment.EnvironmentVar
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import javax.sql.DataSource

val databaseModule = module {

    singleOf(::DatabaseLoader)
    single {
        Database.connect(datasource = get<DataSource>())
    }

    single<DataSource> {
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
}
