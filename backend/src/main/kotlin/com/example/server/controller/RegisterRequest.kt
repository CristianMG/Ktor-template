package com.example.server.controller

import com.example.domain.model.GenderModel
import com.example.server.util.validate.ValidateDate
import io.fluidsonic.country.Country
import kotlinx.serialization.Serializable
import org.valiktor.functions.*
import org.valiktor.validate

@Serializable
data class RegisterRequest(
    val name: String,
    val lastname: String,
    val email: String,
    val password: String,
    val pushToken: String,
    val gender: String,
    val weight: Int,
    val height: Int,
    val birthday: String,
    val country: String
) {
    init {
        validate(this) {
            validate(RegisterRequest::name).isNotBlank()
            validate(RegisterRequest::lastname).isNotBlank()
            validate(RegisterRequest::email).isEmail()
            validate(RegisterRequest::password).isNotBlank().hasSize(min = 8)
            validate(RegisterRequest::pushToken).isNotBlank()
            validate(RegisterRequest::gender).isIn(GenderModel.values().map { it.name })
            validate(RegisterRequest::weight).isBetween(20, 300)
            validate(RegisterRequest::height).isBetween(50, 300)
            validate(RegisterRequest::birthday).isValid {
                ValidateDate.localDate(it)
            }
            validate(RegisterRequest::country).isValid {
                Country.forCodeOrNull(it) != null
            }
        }
    }
}
