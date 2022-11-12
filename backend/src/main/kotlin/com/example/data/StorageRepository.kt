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

package com.example.data

import com.example.server.environment.EnvironmentVar
import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.UploadObjectArgs
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File


class StorageRepository(
    private val environmentVar: EnvironmentVar
) {

    private val client: MinioClient by lazy {
        MinioClient.builder().endpoint(environmentVar.minioURL).credentials(environmentVar.minioUser, environmentVar.minioPassword).build()
    }


    fun makeBucket(key: String) {
        if (!existBucket(key)) {
            client.makeBucket(
                MakeBucketArgs.builder()
                    .bucket(key)
                    .build()
            )
        }
    }

    fun uploadFile(bucket: String, name: String, file: File) {
        makeBucket(bucket)
        client.uploadObject(
            UploadObjectArgs.builder()
                .bucket(bucket)
                .`object`("$name.${file.extension}")
                .filename(file.absolutePath)
                .build()
        )
    }

    fun existBucket(bucketName: String): Boolean =
        client.bucketExists(
            BucketExistsArgs.builder()
                .bucket(bucketName)
                .build()
        )


    companion object {
        val USERS = "users"




        fun getBucketUser(userId: String): String = "$USERS/$userId"
        fun getBucketUserProfile(userId: String): String = "${getBucketUser(userId)}/user_image"
    }
}
