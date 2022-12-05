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

import com.example.data.entity.RoleType
import com.example.domain.model.GenderModel
import com.example.domain.usecase.RegisterUseCase
import com.example.server.dto.request.RegisterRequestDTO
import io.github.serpro69.kfaker.faker

class UserSeed(
    private val registerUseCase: RegisterUseCase
) {
    fun seed() {
        val faker = faker { }
        registerUseCase(
            RegisterUseCase.RegisterUseCaseParam(
                RegisterRequestDTO(
                    "admin",
                    "admin",
                    "admin@example.com",
                    "admin",
                    "no-push-token",
                    GenderModel.MAN.name,
                    86,
                    180,
                    "1990-01-01",
                    "ES"
                ), RoleType.ADMIN
            )
        )

        /*     registerUseCase(
                 faker.name.firstName(),
                 faker.name.lastName(),
                 faker.internet.email(),
                 faker.random.nextUUID(),
                 GenderModel.WOMAN,
                 faker.random.nextInt(40, 100),
                 faker.random.nextInt(100, 200),
                 LocalDate.now(),
                 faker.address.countryCode(),
                 RoleType.USER,
                 "test-user",
                 faker.random.randomString(200),
                 System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7
             )*/
    }
}
