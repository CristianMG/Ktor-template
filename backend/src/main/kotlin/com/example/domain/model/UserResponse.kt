package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String? = null,
    val name: String,
    val lastName: String,
    val email: String,
    val pushToken: String,
    val gender: GenderModel,
    val weight: Int,
    val height: Int,
    val birthday: String,
    val country: String,
)
