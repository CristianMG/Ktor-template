object BuildConstants {

    object MainSettings {
        const val kotlinVersion = "1.7.10"
    }

    val dependencies = mutableListOf<String>().apply {
    }

    val testDependencies = mutableListOf<String>().apply {
    }

    object Classpath {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${MainSettings.kotlinVersion}"
        const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:10.3.0"
    }

}
