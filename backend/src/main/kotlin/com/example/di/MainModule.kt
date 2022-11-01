package com.example.di

import io.ktor.server.application.*
import org.koin.dsl.module

fun getMainModule(application: Application) = module {
    single { application }
}