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

package com.example.domain.usecase

import com.example.data.repository.StorageRepository
import com.example.data.repository.UserRepository
import com.example.domain.model.UserModel
import java.io.File

class UpdateUserImageCase(
    private val userRepository: UserRepository,
    private val storageRepository: StorageRepository
) {

    operator fun invoke(file: File, userId: String): UserModel {
        val user = userRepository.findById(userId)!!
        val multimediaModel = storageRepository.uploadUserImage(userId, file)
        user.profileImage = multimediaModel
        return user
    }
}
