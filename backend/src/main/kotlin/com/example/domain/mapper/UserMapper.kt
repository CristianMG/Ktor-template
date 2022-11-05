package com.example.domain.mapper

import com.example.data.UserEntity
import com.example.domain.model.UserResponse
import java.time.format.DateTimeFormatter

class UserMapper : IMapper<UserEntity, UserResponse> {

    override fun toModel(entity: UserEntity): UserResponse =
        UserResponse(
            entity.id.toString(),
            entity.name,
            entity.lastName,
            entity.email,
            entity.pushToken,
            entity.gender,
            entity.weight,
            entity.height,
            entity.birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            entity.country,
        )
}
