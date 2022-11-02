package com.example.di

import com.example.server.environment.EnvironmentVar
import com.example.server.plugins.HttpConfiguration
import com.example.server.plugins.Serialization
import com.example.server.plugins.RoutingConfiguration
import com.example.server.plugins.SwaggerUiConfiguration
import com.example.server.plugins.ValidatorConfiguration
import com.example.server.plugins.StatusPageConfiguration
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