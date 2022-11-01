package com.example.domain.exception

sealed class Failure: Throwable() {
    object ServerError : Failure()
}