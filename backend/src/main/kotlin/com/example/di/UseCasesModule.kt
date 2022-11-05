package com.example.di

import com.example.domain.usecase.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCasesModule = module {
    singleOf(::LoginUseCase)
    singleOf(::RegisterUseCase)
}
