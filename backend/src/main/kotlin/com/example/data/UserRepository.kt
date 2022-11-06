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

package com.example.data

import com.example.domain.model.GenderModel
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class UserRepository() {
    fun findByEmail(email: String): UserEntity? = transaction {
        UserEntity.find { Users.email eq email }.firstOrNull()
    }

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
            }
        }

}
