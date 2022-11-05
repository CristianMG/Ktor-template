package com.example.di

import com.example.server.environment.EnvironmentVar
import com.example.server.plugins.*
import com.example.server.security.JWTSecurity
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val configurationModule = module {
    singleOf(::EnvironmentVar)
    singleOf(::JWTSecurity)
    singleOf(::HttpConfiguration)
    singleOf(::Serialization)
    singleOf(::RoutingConfiguration)
    singleOf(::SwaggerUiConfiguration)
    singleOf(::ValidatorConfiguration)
    singleOf(::StatusPageConfiguration)
}
