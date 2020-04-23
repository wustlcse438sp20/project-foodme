package com.example.foodme.Activities.Network

import com.example.foodme.Activities.Data.Places
import com.example.foodme.Activities.Data.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import retrofit2.Response

interface PlaceInterface {


    /*   This does not work. So I decided to use the entire url as a paramter to get it work.
        @GET("textsearch/json?")
        fun getRestaurants(@Query("query") query: String)
                : Call<Places>;
    */
    @GET
    fun getNearbyRestaurants(@Url url:String)
            : Call<Places>;

    @GET
    fun getRestaurantDetails(@Url url:String)
            : Call<Restaurant>
}