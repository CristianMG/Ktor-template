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
import io.ktor.server.auth.*
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.timestamp
import java.util.*

object Users : UUIDTable("users") {
    val name = varchar("name", 50)
    val lastName = varchar("lastName", 50)
    val email = varchar("email", 50)
    val pushToken = varchar("pushToken", 50)
    val gender = enumerationByName("gender", 50, GenderModel::class)
    val weight = integer("weight")
    val height = integer("height")
    val birthday = date("birthday")
    val country = varchar("country", 50)
    val role = enumerationByName("role", 50, RoleType::class)
    val password = varchar("password", 200)
    val hashedRt = varchar("hashedRt", 200)
    val expirationRt = timestamp("expirationRt")
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id), Principal {

    companion object : UUIDEntityClass<UserEntity>(Users)

    var name by Users.name
    var lastName by Users.lastName
    var email by Users.email
    var pushToken by Users.pushToken
    var gender by Users.gender
    var weight by Users.weight
    var height by Users.height
    var birthday by Users.birthday
    var country by Users.country
    var role by Users.role
    var password by Users.password
    var hashedRt by Users.hashedRt
    var expirationRt by Users.expirationRt
}
