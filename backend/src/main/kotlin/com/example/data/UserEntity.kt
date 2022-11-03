package com.example.data

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.util.*


object Users : UUIDTable("users") {
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 200)
    val role = enumerationByName("role", 50, RoleType::class)
    val hashedRt = varchar("hashedRt", 200)
    val expirationRt = timestamp("expirationRt")
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<UserEntity>(Users)

    var name by Users.name
    var email by Users.email
    var password by Users.password
    var role by Users.role
    var hashedRt by Users.hashedRt
    var expirationRt by Users.expirationRt

}