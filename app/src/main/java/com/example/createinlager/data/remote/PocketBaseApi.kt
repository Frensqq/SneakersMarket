package com.example.createinlager.data.remote

import com.example.createinlager.data.model.AuthResponse
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.OtpResponse
import com.example.createinlager.data.model.SneakersView
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.domain.model.ChangePassRequest
import com.example.createinlager.domain.model.OtpAuth
import com.example.createinlager.domain.model.OtpRequest
import com.example.createinlager.domain.model.UserAuth
import com.example.createinlager.domain.model.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @PATCH("/api/collections/users/records/{userId}")
    fun changePassword(@Path("userId") userId: String, @Header("Authorization") token: String, @Body request: ChangePassRequest ): Call<UserResponse>

    //Вывод товаров

    @GET("/api/collections/sneakers/records")
    fun ViewSneakers(@Query("filter") filter: String, @Query("sort") sort: String, @Query("perPage") perPage: Int ): Call<SneakersView>

    //Вывод товара если он в избраном

    @GET("/api/collections/Love/records")
    fun checkFavorite(@Query("filter") filter: String): Call<FavoriteList>


}