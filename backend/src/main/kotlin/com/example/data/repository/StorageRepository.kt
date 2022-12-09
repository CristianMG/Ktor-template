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

import com.example.data.entity.MultimediaEntity
import com.example.domain.model.MultimediaModel
import com.example.server.environment.EnvironmentVar
import io.minio.*
import io.minio.http.Method
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

class StorageRepository(
    private val environmentVar: EnvironmentVar
) {

    private val client: MinioClient by lazy {
        MinioClient.builder().endpoint(environmentVar.minioURL).credentials(environmentVar.minioUser, environmentVar.minioPassword).build()
    }

    fun getMultimediaById(id: String): MultimediaModel? {
        return transaction {
            MultimediaEntity.findById(UUID.fromString(id))?.toModel()
        }
    }

    fun makeBucket(key: String) {
        if (!existBucket(key)) {
            client.makeBucket(
                MakeBucketArgs.builder().bucket(key).build()
            )
        }
    }

    fun uploadFile(bucket: String, path: String, file: File): MultimediaModel {
        makeBucket(bucket)
        val item = client.uploadObject(
            UploadObjectArgs.builder()
                .bucket(bucket)
                .`object`("$path.${file.extension}")
                .filename(file.absolutePath)
                .build()
        ).`object`()

        return transaction {
            MultimediaEntity.new {
                this.bucket = bucket
                this.location = item
                this.extension = file.extension
                this.lenght = file.length()
                this.creationDate = System.currentTimeMillis()
            }.toModel()
        }
    }

    fun uploadUserImage(userId: String, file: File): MultimediaModel {
        return uploadFile(BUCKET_USERS, getPathUserImage(userId), file)
    }

    private fun getLink(bucket: String, item: String): String? {
        return try {
            client.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucket)
                    .`object`(item)
                    .expiry(2, TimeUnit.HOURS).build()
            )
        } catch (e: Exception) {
            null
        }
    }

    fun getLink(id: String): String? {
        val entity = getMultimediaById(id) ?: return null
        return getLink(entity.bucket, entity.location)
    }

    fun existBucket(bucketName: String): Boolean = client.bucketExists(
        BucketExistsArgs.builder().bucket(bucketName).build()
    )

    companion object {
        private const val BUCKET_USERS = "users"
        const val PROFILE_IMAGE_OBJECT = "profile_image"

        fun getPathUserImage(userId: String): String = "$userId/$PROFILE_IMAGE_OBJECT"
    }
}
