package com.example.server

import com.example.di.*
import com.example.server.environment.EnvironmentVar
import com.example.server.plugins.*
import com.example.server.security.JWTSecurity
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
                    getMainModule(this@module), configurationModule, routeModule, controllerModule,useCasesModule
                )
            }

            val env: EnvironmentVar by inject()
            val jwtSecurity: JWTSecurity by inject()
            val httpConfiguration: HttpConfiguration by inject()
            val serialization: Serialization by inject()
            val routingConfiguration: RoutingConfiguration by inject()
            val swaggerUiConfiguration: SwaggerUiConfiguration by inject()
            val validator: ValidatorConfiguration by inject()
            val statusPageConfiguration: StatusPageConfiguration by inject()

            jwtSecurity.configure()
            httpConfiguration.configure()
            serialization.configure()
            routingConfiguration.configure()
            swaggerUiConfiguration.configure()
            validator.configure()
            statusPageConfiguration.configure()


            connector {
                port = env.portListen
                host = env.ipListen
            }
        }


    })

        .start(wait = true)
}