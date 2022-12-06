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

package com.example.data.entity

import com.example.domain.model.MultimediaModel
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Multimedia : UUIDTable("multimedia") {
    val bucket = varchar("bucket", 25)
    val location = varchar("name", 150)
    val extension = varchar("extension", 25)
    val lenght = long("lenght")
    val creationDate = long("creationDate")
}

class MultimediaEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<MultimediaEntity>(Multimedia)

    var bucket by Multimedia.bucket
    var location by Multimedia.location
    var extension by Multimedia.extension
    var lenght by Multimedia.lenght
    var creationDate by Multimedia.creationDate


    fun toModel() = MultimediaModel(
        id.value.toString(),
        bucket,
        location,
        extension,
        lenght,
        creationDate
    )
}
