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

import com.example.domain.model.TempAuthModel
import com.example.server.environment.EnvironmentVar
import com.mailgun.model.message.Message

class MailRepository(
    private val environmentVar: EnvironmentVar,
    private val mainGunMessagesApi: com.mailgun.api.v3.MailgunMessagesApi
) {

    fun sendMessageConfirmMail(tempAuth: TempAuthModel) {
        mainGunMessagesApi.sendMessage(
            environmentVar.emailApi,
            Message.builder()
                .from("Test <${environmentVar.emailApi}>")
                .to(tempAuth.user.email)
                .html(
                    """
                        <html>
                            <body>
                                <h1>Confirm your email</h1>
                                <p>Click on the link to confirm your email</p>
                                <a href="https://${environmentVar.ipListen}/user/confirm-email?token=${tempAuth.id}">Confirm email</a>
                            </body>
                        </html>
                    """.trimIndent()
                )
                .build()
        )
    }
}
