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

package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String? = null,
    val name: String,
    val lastName: String,
    val email: String,
    val pushToken: String,
    val gender: GenderModel,
    val weight: Int,
    val height: Int,
    val birthday: String,
    val country: String,
)
