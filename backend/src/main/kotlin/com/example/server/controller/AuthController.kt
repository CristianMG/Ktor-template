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

package com.example.server.controller

import com.example.data.RoleType
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.RegisterUseCase
import com.example.server.dto.request.LoginRequestDTO
import com.example.server.dto.request.RegisterRequestDTO
import com.example.server.dto.response.SessionResponseDTO
import com.example.server.dto.wrapResponse

class AuthController(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase
) {

    fun login(loginRequest: LoginRequestDTO) = wrapResponse {
        loginUseCase(loginRequest.email, loginRequest.password)
            .apply {
                SessionResponseDTO(this)
            }
    }

    fun register(request: RegisterRequestDTO) = wrapResponse {
        registerUseCase(
            RegisterUseCase.RegisterUseCaseParam(request, RoleType.USER)
        ).apply {
            SessionResponseDTO(this)
        }
    }
}
