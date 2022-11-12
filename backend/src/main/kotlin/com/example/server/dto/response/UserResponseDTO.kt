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

package com.example.server.dto.response

import com.example.domain.model.GenderModel
import com.example.domain.model.UserModel
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class UserResponseDTO(
    val id: String? = null,
    val name: String,
    val lastName: String,
    val email: String,
    val pushToken: String,
    val gender: Int,
    val weight: Int,
    val height: Int,
    val birthday: String,
    val country: String,
    val profilePicture: String? = null,
) {
    constructor(userModel: UserModel) : this(
        userModel.id,
        userModel.name,
        userModel.lastName,
        userModel.email,
        userModel.pushToken,
        userModel.gender.key,
        userModel.weight,
        userModel.height,
        userModel.birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        userModel.country,
        userModel.profilePicture
    )
}
