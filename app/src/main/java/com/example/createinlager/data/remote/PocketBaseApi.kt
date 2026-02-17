package com.example.createinlager.data.remote

import com.example.createinlager.data.model.AuthResponse
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.OtpResponse
import com.example.createinlager.data.model.SneakersView
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.ListOrderResponse
import com.example.createinlager.data.model.OrderResponse
import com.example.createinlager.data.model.PozitionResponse
import com.example.createinlager.data.model.UserUpdate
import com.example.createinlager.domain.model.CartRequest
import com.example.createinlager.domain.model.ChangePassRequest
import com.example.createinlager.domain.model.OrderRequest
import com.example.createinlager.domain.model.OtpAuth
import com.example.createinlager.domain.model.OtpRequest
import com.example.createinlager.domain.model.PozitionRequest
import com.example.createinlager.domain.model.UserAuth
import com.example.createinlager.domain.model.UserRequest
import com.example.createinlager.domain.model.favoriteRequest
import com.example.professionals.data.model.market.ListInCart
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    //Просмотр пользоавтеля

    @GET("/api/collections/users/records/{userId}")
    fun UserView(@Path("userId") userId: String,  @Header("Authorization") token: String ): Call<UserResponse>

    //Обновление пользователя

    @PATCH("/api/collections/users/records/{userId}")
    fun UserUpdate(@Body request: UserUpdate, @Header("Authorization") token: String, @Path("userId") userId: String): Call<UserResponse>

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

    //Добавление заказа в избранное

    @POST("/api/collections/Love/records")
    fun addFavorite(@Body request: favoriteRequest): Call<FavoriteList>

    //Удаление заказа из избранного
    @DELETE("/api/collections/Love/records/{id}")
    fun deleteFavorite(@Path("id") id: String): Call<Unit>

    //корзина

    @POST("/api/collections/Cart/records")
    fun addToСart(@Body request: CartRequest): Call<ListInCart>

    @DELETE("/api/collections/Cart/records/{id}")
    fun deleteСart(@Path("id") id: String): Call<Unit>

    @GET("/api/collections/Cart/records")
    fun ViewСart(@Query("filter") filter: String, @Query("sort") sort: String, @Query("perPage") perPage: Int ): Call<ListInCart>

    @PATCH("/api/collections/Cart/records/{id}")
    fun updateCart(@Path("id") id: String,@Body request: CartRequest ): Call<ListInCart>

    //заказ

    @POST("/api/collections/Orders/records")
    fun createOrder(@Body request: OrderRequest): Call<OrderResponse>

    @GET("/api/collections/Orders/records")
    fun viewOrder(@Query("filter") filter: String): Call<ListOrderResponse>

    @POST("/api/collections/pozition/records")
    fun createPozition(@Body request: PozitionRequest): Call<PozitionResponse>


    //акция
    @GET("/api/collections/Promo/records")
    fun viewPromo(): Call<ListOrderResponse>

}