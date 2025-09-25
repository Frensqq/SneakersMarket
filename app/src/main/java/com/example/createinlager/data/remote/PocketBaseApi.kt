package com.example.createinlager.data.remote

import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.domain.model.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PocketBaseApi {

    ///Регистрация

    @POST("/api/collections/users/records")
    fun RegistrationUser(@Body request: UserRequest ): Call<UserResponse>

    ///Авторизация

    @POST("/api/collections/users/auth-with-password")
    fun AuthPass(@Body request: UserRequest ): Call<UserResponse>



}