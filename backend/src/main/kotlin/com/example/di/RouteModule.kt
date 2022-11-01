package com.example.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.server.route.UserRoute
import com.example.server.route.AuthRoute

val routeModule = module {
    singleOf(::UserRoute)
    singleOf(::AuthRoute)
}