object BuildConstants {

    object MainSettings {
        const val kotlinVersion = "1.7.20"
        const val sqldelight = "1.5.3"
        const val ktlint = "10.3.0"
    }

    val ktor_version = "2.1.3"
    val logback_version = "1.2.11"
    val koin_ktor_version = "3.2.2"
    val dot_env_version = "6.3.1"
    val swagger_ui_version = "0.7.0"
    val valiktor_version = "0.7.0"

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
    }

    val testDependencies = mutableListOf<String>().apply {
        add("io.ktor:ktor-server-tests-jvm:$ktor_version")
        add("org.jetbrains.kotlin:kotlin-test-junit:${MainSettings.kotlinVersion}")
    }

    object Classpath {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${MainSettings.kotlinVersion}"
        const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:10.3.0"
        const val sqldelight = "com.squareup.sqldelight:gradle-plugin:${MainSettings.sqldelight}"
    }

}
