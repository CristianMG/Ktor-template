
plugins {
    application
    kotlin("jvm")
    id("io.ktor.plugin") version "2.1.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20"
}

group = "com.example"
version = "0.0.1"


application {
    mainClass.set("com.example.server.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    BuildConstants.dependencies.forEach {
        implementation(it)
    }

    BuildConstants.testDependencies.forEach {
        testImplementation(it)
    }
}