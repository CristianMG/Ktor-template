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

package com.example.server.util

import io.ktor.http.content.*
import java.io.File
import java.nio.file.Files
import kotlin.io.path.writeBytes

fun PartData.FileItem.getAsTempFile(): File {
    return Files.createTempFile(null, ".${originalFileName?.getExtensionOrNull()}")
        .apply {
            writeBytes(streamProvider().readBytes())
        }.toFile()
}