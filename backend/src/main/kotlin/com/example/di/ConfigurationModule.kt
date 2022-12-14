/*
 * Copyright (c) 2022 CristianMg <https://github.com/CristianMG>
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.di

import com.example.server.plugins.*
import com.example.server.plugins.serialization.PluginSerialization
import com.example.server.security.JWTSecurity
import io.github.serpro69.kfaker.faker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val configurationModule = module {
    singleOf(::JWTSecurity)
    singleOf(::PluginHttpConfiguration)
    singleOf(::PluginSerialization)
    singleOf(::PluginRoutingConfiguration)
    singleOf(::PluginSwaggerUiConfiguration)
    singleOf(::PluginStatusPageConfiguration)
    singleOf(::PluginConfigurator)
    single { faker { } }
}
