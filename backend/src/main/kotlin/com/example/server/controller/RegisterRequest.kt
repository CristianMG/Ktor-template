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

package com.example.server.controller

import com.example.domain.model.GenderModel
import com.example.server.util.validate.ValidateDate
import io.fluidsonic.country.Country
import kotlinx.serialization.Serializable
import org.valiktor.functions.*
import org.valiktor.validate

@Serializable
data class RegisterRequest(
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
            validate(RegisterRequest::name).isNotBlank()
            validate(RegisterRequest::lastname).isNotBlank()
            validate(RegisterRequest::email).isEmail()
            validate(RegisterRequest::password).isNotBlank()
            validate(RegisterRequest::pushToken).isNotBlank()
            validate(RegisterRequest::gender).isIn(GenderModel.values().map { it.name })
            validate(RegisterRequest::weight).isBetween(20, 300)
            validate(RegisterRequest::height).isBetween(50, 300)
            validate(RegisterRequest::birthday).isValid {
                ValidateDate.localDate(it)
            }
            validate(RegisterRequest::country).isValid {
                Country.forCodeOrNull(it) != null
            }
        }
    }
}
