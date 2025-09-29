package com.example.createinlager.domain.model

data class ChangePassRequest(

    val oldPassword: String,
    val password: String,
    val passwordConfirm: String

)
