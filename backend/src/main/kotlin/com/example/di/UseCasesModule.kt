package com.example.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.domain.usecase.LoginUseCase

val useCasesModule = module {
    singleOf(::LoginUseCase)
}