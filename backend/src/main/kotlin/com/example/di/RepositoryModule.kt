package com.example.di

import com.example.data.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepository)
}
