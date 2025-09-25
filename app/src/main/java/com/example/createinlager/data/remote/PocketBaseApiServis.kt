package com.example.createinlager.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PocketBaseApiServis {

    private const val BASE_URL = "http://127.0.0.1:8090"

    val instance: PocketBaseApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PocketBaseApi::class.java)
    }


}