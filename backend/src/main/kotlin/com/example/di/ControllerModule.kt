package com.example.di

import com.example.server.controller.AuthController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val controllerModule = module {
    singleOf(::AuthController)
}
