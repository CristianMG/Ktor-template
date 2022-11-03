package com.example.data.mapper

import com.example.data.UserEntity
import com.example.domain.model.UserModel
import java.time.Instant

class UserMapper : IMapper<UserEntity, UserModel> {

    override fun toModel(entity: UserEntity): UserModel {
        return UserModel(
            id = entity.id.value.toString(),
            name = entity.name,
            email = entity.email,
            password = entity.password,
            role = entity.role,
            hashedRt = entity.hashedRt,
            expirationRt = entity.expirationRt.toEpochMilli()
        )
    }

    override fun toEntity(model: UserModel): UserEntity {
        return UserEntity.new {
            name = model.name
            email = model.email
            password = model.password
            role = model.role
            hashedRt = model.hashedRt
            expirationRt = Instant.ofEpochMilli(model.expirationRt)
        }
    }
}