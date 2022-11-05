package com.example.server.util.validate

import java.time.LocalDate

object ValidateDate {

    fun localDate(date: String): Boolean {
        return try {
            LocalDate.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }
}
