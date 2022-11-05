package com.example.data.seed

import com.example.data.UserRepository
import io.github.serpro69.kfaker.faker

class UserSeed(
    private val userRepository: UserRepository
) {
    fun seed() {
        val faker = faker { }

        faker.name.firstName()
        faker.address.city()

    /*    userRepository.saveUser(
            null,
            "admin",
            "admin@example.com",
            "admin",
            RoleType.ADMIN,
            faker.random.randomString(200),
            System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7

        )

        userRepository.saveUser(
            UserResponse(
                null,
                faker.name.firstName(),
                faker.internet.email(),
                faker.random.nextUUID(),
                RoleType.USER,
                faker.random.randomString(200),
                System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7
            )
        )*/
    }
}
