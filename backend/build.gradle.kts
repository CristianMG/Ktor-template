import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("io.ktor.plugin") version "2.1.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20"
}

group = "com.example"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "${JavaVersion.VERSION_11}"
            freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
        }
    }
}
application {
    mainClass.set(System.getProperty("exec.mainClass") ?: "com.example.server.ApplicationKt")
    val isDevelopment: Boolean = true
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
