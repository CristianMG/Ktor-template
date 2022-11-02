package com.example.server.response

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GenericResponse<T>(
    val data: T,
    val common: CommonResponse? = null,
    val requestId: String = UUID.randomUUID().toString(),
)

@Serializable
class CommonResponse()

fun <T> wrapResponse(closure: () -> T) = GenericResponse(closure(), null)