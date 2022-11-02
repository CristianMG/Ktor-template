package com.example.server.controller

import com.example.domain.usecase.LoginParam
import com.example.domain.usecase.LoginUseCase
import com.example.server.response.wrapResponse
import io.ktor.server.application.*
import kotlinx.serialization.Serializable

class AuthController(
    val application: Application, val loginUseCase: LoginUseCase
) {

    fun login(loginRequest: LoginRequest) = wrapResponse {
        LoginResponse(
            loginUseCase.invoke(LoginParam(loginRequest.email, loginRequest.password)),
            ""
        )
    }

    @Serializable
    data class LoginResponse(
        val token: String,
        val refreshToken: String,
    )
}