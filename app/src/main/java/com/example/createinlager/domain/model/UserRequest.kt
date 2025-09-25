package com.example.createinlager.domain.model

data class UserRequest(

    val name: String,
    val email:String,
    val password:String,
    val passwordConfirm:String

)
