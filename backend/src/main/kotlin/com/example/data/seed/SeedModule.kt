package com.example.data.seed

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val seedModule = module {
    singleOf(::UserSeed)
}
