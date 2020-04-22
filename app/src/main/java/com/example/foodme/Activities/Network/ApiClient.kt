package com.example.foodme.Activities.Network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    const val BASE_URL = "https://maps.googleapis.com/maps/api/"

    fun makeRetrofitService(): PlaceInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PlaceInterface::class.java)
    }
}