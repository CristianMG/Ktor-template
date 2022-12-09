package com.example.utils

import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import java.io.File
import java.nio.file.Files

/*
* Download and check if url is properly formed
* */
suspend fun HttpClient.checkImage(path: String) {
    get(path).let { imageResponse ->
        imageResponse.status shouldBe HttpStatusCode.OK
        imageResponse.headers[HttpHeaders.ContentType] shouldBe ContentType.Image.JPEG.toString()
        val body = imageResponse.bodyAsChannel()
        body.availableForRead shouldBeGreaterThan 0
        val fileDownloaded = Files.createTempFile(null, ".test").toFile()
        val writeChannel = fileDownloaded.writeChannel()
        body.copyAndClose(writeChannel)
        writeChannel.flush()

        fileDownloaded.exists() shouldBe true
        fileDownloaded.length() shouldBeGreaterThan 0
    }
}

fun FormBuilder.addFile(parameter: String, file: File) {
    append(
        parameter, file.readBytes(),
        Headers.build {
            append(HttpHeaders.ContentType, ContentType.Image.JPEG.toString())
            append(HttpHeaders.ContentDisposition, "form-data; name=\"${parameter}\"; filename=\"${file.name}\"")
        }
    )
}
