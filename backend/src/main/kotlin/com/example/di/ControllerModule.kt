package com.example.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.server.controller.AuthController

val controllerModule = module {
    singleOf(::AuthController)
}