package com.example.data

import com.example.data.seed.UserSeed
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseLoader(
    val database: Database,
    val userSeed: UserSeed
) {

    fun load() {
        transaction {
            //   if (database.version.longValueExact() == 0L) {
            SchemaUtils.create(Users)
            userSeed.seed()
            // }
        }
    }
}
