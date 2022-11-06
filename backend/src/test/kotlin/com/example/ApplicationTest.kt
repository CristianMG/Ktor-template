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

package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.server.util.*
import org.junit.Test
import org.koin.test.KoinTest
import kotlin.test.assertEquals

class ApplicationTest: KoinTest {


    @Test
    fun testRoot() = testApplication {
        client.config {
            url {
                protocol = URLProtocol.HTTP
                host = "0.0.0.0"
                port = 3000
            }
        }
        
        val response = client.get("/")
        println(response.status)
        assertEquals(HttpStatusCode.OK, response.status)
      //  assertEquals("Hello, world!", response.bodyAsText())
    }
/*

    companion object {
        lateinit var testApp: TestApplication
        @JvmStatic
        @BeforeAll
        fun setup()  {
            testApp = TestApplication {  }
        }

        @JvmStatic
        @AfterAll
        fun teardown() {
            testApp.stop()
        }
    }*/


}
