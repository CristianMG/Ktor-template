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

import com.example.data.entity.Multimedia
import com.example.data.entity.RoleType
import com.example.data.entity.UserEntity
import com.example.data.entity.Users
import com.example.data.entity.Users.nullable
import com.example.domain.model.GenderModel
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class UserRepository {
    fun findByEmail(email: String): UserEntity? = transaction {
        UserEntity.find { Users.email eq email }.firstOrNull()
    }

    fun findById(id: String): UserEntity? = transaction {
        UserEntity.findById(UUID.fromString(id))
    }

/*
    fun updateUser(user: UserEntity) = transaction {
        Users.update({ Users.id eq user.id }) {
            it[email] = user.email
            it[password] = user.password
            it[name] = user.name
            it[lastName] = user.lastName
            it[email] = user.email
            it[pushToken] = user.pushToken
            it[gender] = user.gender
            it[weight] = user.weight
            it[height] = integer("height")
            it[birthday] = date("birthday")
            it[country] = varchar("country", 50)
            it[role] = enumerationByName("role", 50, RoleType::class)
            it[password] = varchar("password", 200)
            it[hashedRt] = varchar("hashedRt", 200)
            it[expirationRt] = timestamp("expirationRt")
            it[profileImage] = reference("multimedia", Multimedia).nullable()
        }
    }
*/


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
                this.profileImage = null
            }
        }

}
