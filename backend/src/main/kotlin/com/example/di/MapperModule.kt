package com.example.di

import com.example.domain.mapper.UserMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mapperModule = module {
    singleOf(::UserMapper)
}
