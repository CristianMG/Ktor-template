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

package com.example.data.seed

import com.example.data.DatabaseLoader
import com.example.di.*
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() {
    startKoin {
        modules(
            repositoryModule, databaseModule, seedModule, environmentModule, useCasesModule
        )
    }

    val loader: DatabaseLoader by inject(DatabaseLoader::class.java)
    val userSeed: UserSeed by inject(UserSeed::class.java)
    loader.connect()
    userSeed.seed()
}
