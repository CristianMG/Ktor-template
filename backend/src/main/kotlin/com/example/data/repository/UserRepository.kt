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

package com.example.data.repository

import com.example.data.entity.*
import com.example.domain.model.GenderModel
import com.example.domain.model.UserModel
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class UserRepository {
    fun findByEmail(email: String): UserModel? = transaction {
        UserEntity.find { Users.email eq email }.firstOrNull()?.toModel()
    }

    fun findById(id: String): UserModel? = transaction {
        UserEntity.findById(UUID.fromString(id))?.toModel()
    }

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
    ): UserModel =
        transaction {
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
            }.toModel()
        }

    fun update(user: UserModel): UserModel {
        transaction {
            Users.update({ Users.id eq UUID.fromString(user.id) }) {
                it[this.name] = user.name
                it[lastName] = user.lastName
                it[email] = user.email
                it[pushToken] = user.pushToken
                it[gender] = user.gender
                it[weight] = user.weight
                it[height] = user.height
                it[birthday] = user.birthday
                it[country] = user.country
                it[role] = user.role
                it[password] = user.password
                it[hashedRt] = user.refreshToken
                it[expirationRt] = user.expirationRefreshToken
                it[validateEmail] = user.isEmailValidated
            }
        }
        return findById(user.id)!!
    }
}
