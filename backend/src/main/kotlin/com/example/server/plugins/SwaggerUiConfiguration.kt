package com.example.server.plugins

import com.example.server.environment.EnvironmentVar
import io.ktor.server.application.*
import io.github.smiley4.ktorswaggerui.SwaggerUI

class SwaggerUiConfiguration(
    private val application: Application,
    private val environmentVar: EnvironmentVar
) {
    fun configure() {
        application.apply {
            install(SwaggerUI){
                swagger {
                    swaggerUrl =environmentVar.swaggerEndpoint
                    forwardRoot = true
                }
                info {
                    title = "Swagger Documentation"
                    version = "latest"
                    description = "Example API for testing and demonstration purposes."
                }
                server {
                    url =  environmentVar.swaggerUrl
                    description = "${environmentVar.environment} Server"
                }
            }
        }
    }
}