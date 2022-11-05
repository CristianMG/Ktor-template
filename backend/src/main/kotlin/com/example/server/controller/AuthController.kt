package com.example.server.controller

import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.RegisterUseCase
import com.example.server.response.wrapResponse
import io.ktor.server.application.*

class AuthController(
    val application: Application,
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase
) {

    fun login(loginRequest: LoginRequest) = wrapResponse {
        loginUseCase(loginRequest)
    }

    fun register(request: RegisterRequest) = wrapResponse {
        registerUseCase(request)
    }
}
