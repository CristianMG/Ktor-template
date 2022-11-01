package com.example

import com.example.di.configurationModule
import com.example.di.getMainModule
import com.example.di.routeModule
import com.example.server.environment.EnvironmentVar
import com.example.plugins.*
import com.example.server.security.JWTSecurity
import com.example.server.plugins.HttpConfiguration
import com.example.server.plugins.RoutingConfiguration
import com.example.server.plugins.Serialization
import com.example.server.plugins.SwaggerUiConfiguration
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {

    embeddedServer(Netty, environment = applicationEngineEnvironment {
        module {
            install(Koin) {
                modules(
                    getMainModule(this@module), configurationModule, routeModule
                )
            }

            val env: EnvironmentVar by inject()
            val jwtSecurity: JWTSecurity by inject()
            val httpConfiguration: HttpConfiguration by inject()
            val serialization: Serialization by inject()
            val routingConfiguration: RoutingConfiguration by inject()
            val swaggerUiConfiguration: SwaggerUiConfiguration by inject()

            jwtSecurity.configure()
            httpConfiguration.configure()
            serialization.configure()
            routingConfiguration.configure()
            swaggerUiConfiguration.configure()


            connector {
                port = env.portListen
                host = env.ipListen
            }
        }


    })

        .start(wait = true)
}