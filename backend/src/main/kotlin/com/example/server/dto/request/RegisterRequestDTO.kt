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

package com.example.server.dto.request

import com.example.domain.model.GenderModel
import com.example.server.util.validate.ValidateDate
import io.fluidsonic.country.Country
import kotlinx.serialization.Serializable
import org.valiktor.functions.*
import org.valiktor.validate

@Serializable
data class RegisterRequestDTO(
    val name: String,
    val lastname: String,
    val email: String,
    val password: String,
    val pushToken: String,
    val gender: String,
    val weight: Int,
    val height: Int,
    val birthday: String,
    val country: String
) {
    init {
        validate(this) {
            validate(RegisterRequestDTO::name).isNotBlank()
            validate(RegisterRequestDTO::lastname).isNotBlank()
            validate(RegisterRequestDTO::email).isEmail()
            validate(RegisterRequestDTO::password).isNotBlank()
            validate(RegisterRequestDTO::pushToken).isNotBlank()
            validate(RegisterRequestDTO::gender).isIn(GenderModel.values().map { it.name })
            validate(RegisterRequestDTO::weight).isBetween(20, 300)
            validate(RegisterRequestDTO::height).isBetween(50, 300)
            validate(RegisterRequestDTO::birthday).isValid {
                ValidateDate.localDate(it)
            }
            validate(RegisterRequestDTO::country).isValid {
                Country.forCodeOrNull(it) != null
            }
        }
    }
}
