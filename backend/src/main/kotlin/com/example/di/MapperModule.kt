package com.example.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.data.mapper.UserMapper

val mapperModule = module {
    singleOf(::UserMapper)
}