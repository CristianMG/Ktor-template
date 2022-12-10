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

package com.example.data.repository

import com.example.data.entity.TempAuthEntity
import com.example.data.entity.UserEntity
import com.example.domain.model.UserModel
import com.example.server.environment.EnvironmentVar
import com.mailgun.model.message.Message
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

class TempAuthRepository(
    private val environmentVar: EnvironmentVar
) {

    fun create(userModel: UserModel) =
        transaction {
            TempAuthEntity.new {
                this.user = UserEntity.findById(UUID.fromString(userModel.id))!!
                this.expiration = Instant.now().plusSeconds(environmentVar.tempAuthExpireTime)
            }.toModel()
        }

    fun getById(id: String) =
        transaction {
            TempAuthEntity.findById(UUID.fromString(id))?.toModel()
        }

}
