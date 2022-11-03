package com.example.data

import com.example.data.mapper.UserMapper
import com.example.domain.model.UserModel
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


class UserRepository(
    val database: Database,
    val userMapper: UserMapper
) {
    fun insert(user: UserModel) {
        transaction {
            userMapper.toEntity(user)
        }
    }
}