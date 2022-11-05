package com.example.di

import com.example.server.route.AuthRoute
import com.example.server.route.UserRoute
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val routeModule = module {
    singleOf(::UserRoute)
    singleOf(::AuthRoute)
}
