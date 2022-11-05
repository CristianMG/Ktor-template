package com.example.data

import com.example.domain.model.GenderModel
import org.jetbrains.exposed.sql.Database
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class UserRepository(
    val database: Database
) {
    fun findByEmail(email: String): UserEntity? =
        UserEntity.find { Users.email eq email }.firstOrNull()

    fun findById(id: String): UserEntity? =
        UserEntity.find { Users.id eq UUID.fromString(id) }.firstOrNull()

    fun saveUser(
        name: String,
        lastName: String,
        email: String,
        pushToken: String,
        gender: GenderModel,
        weight: Int,
        height: Int,
        birthday: LocalDate,
        country: String,
        role: RoleType,
        password: String,
        hashedRt: String,
        expirationRt: Long
    ): UserEntity =
        UserEntity.new {
            this.name = name
            this.lastName = lastName
            this.email = email
            this.pushToken = pushToken
            this.gender = gender
            this.weight = weight
            this.height = height
            this.birthday = birthday
            this.country = country
            this.role = role
            this.password = password
            this.hashedRt = hashedRt
            this.expirationRt = Instant.ofEpochMilli(expirationRt)
        }
}
