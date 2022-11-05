package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SessionResponse(
    val token: String,
    val refreshToken: String,
    val user: UserResponse
)
