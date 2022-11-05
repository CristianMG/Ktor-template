apply(plugin = "org.jlleitschuh.gradle.ktlint")
apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")

buildscript {

    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
        maven { url = uri("https://maven.fabric.io/public") }
    }
    dependencies {
        classpath(BuildConstants.Classpath.kotlinGradlePlugin)
        classpath(BuildConstants.Classpath.ktlintPlugin)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")
    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.fabric.io/public") }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
