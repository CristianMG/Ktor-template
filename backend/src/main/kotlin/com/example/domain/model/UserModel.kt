package com.example.domain.model

import com.example.data.RoleType

data class UserModel(
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String,
    val role: RoleType,
    val hashedRt: String,
    val expirationRt: Long
)