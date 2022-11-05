package com.example.server.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String
)
