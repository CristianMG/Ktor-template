package com.example.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.data.UserRepository

val repositoryModule = module {
    singleOf(::UserRepository)
}