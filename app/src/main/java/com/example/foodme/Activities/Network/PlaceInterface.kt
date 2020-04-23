package com.example.foodme.Activities.Network

import com.example.foodme.Activities.Data.Places
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PlaceInterface {


    /*   This does not work. So I decided to use the entire url as a paramter to get it work.
        @GET("textsearch/json?")
        fun getRestaurants(@Query("query") query: String)
                : Call<Places>;
    */
    @GET
    fun getNearbyRestaurants(@Url url:String)
            : Call<Places>;
}