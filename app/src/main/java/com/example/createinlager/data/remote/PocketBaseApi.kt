package com.example.createinlager.data.remote

import com.example.createinlager.data.model.AuthResponse
import com.example.createinlager.data.model.OtpResponse
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.domain.model.OtpAuth
import com.example.createinlager.domain.model.OtpRequest
import com.example.createinlager.domain.model.UserAuth
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
    fun AuthPass(@Body request: UserAuth ): Call<AuthResponse>

    ///Отправка Otp кода на email

    @POST("/api/collections/users/request-otp")
    fun OTPRequest(@Body request: OtpRequest): Call<OtpResponse>

    ///Вход по Otp коду

    @POST("/api/collections/users/auth-with-otp")
    fun signInWithOTP(@Body request: OtpAuth): Call<AuthResponse>



}