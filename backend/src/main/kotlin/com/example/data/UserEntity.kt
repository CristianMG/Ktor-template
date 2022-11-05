package com.example.data

import com.example.domain.model.GenderModel
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
    val pushToken = varchar("email", 50)
    val gender = enumerationByName("role", 50, GenderModel::class)
    val weight = integer("weight")
    val height = integer("height")
    val birthday = date("birthday")
    val country = varchar("country", 50)
    val role = enumerationByName("role", 50, RoleType::class)
    val password = varchar("password", 200)
    val hashedRt = varchar("hashedRt", 200)
    val expirationRt = timestamp("expirationRt")
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

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
