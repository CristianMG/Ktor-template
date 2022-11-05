package com.example.data.seed

import com.example.di.*
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            repositoryModule, databaseModule, seedModule, mapperModule
        )
    }
}
