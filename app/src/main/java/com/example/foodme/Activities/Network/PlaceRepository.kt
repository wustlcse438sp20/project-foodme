package com.example.foodme.Activities.Network

import android.content.ContentValues.TAG
import android.util.Log
import com.example.foodme.Activities.Common.Common
import com.example.foodme.Activities.Common.Common.currSelectedRestaurant
import com.example.foodme.Activities.Data.Places
import com.example.foodme.Activities.Data.Restaurant
import com.example.foodme.Activities.Data.Results
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceRepository {
    //get the instance of retrofit
    val service = ApiClient.makeRetrofitService()

    fun getNearbyRestaurants(map: GoogleMap, typePlace: String, param : String) {
        Log.e(TAG, param)
        var respond: Call<Places> = service.getNearbyRestaurants(param)
        respond.enqueue(object: Callback<Places> {
            override fun onFailure(call: Call<Places>, t: Throwable) {
                // Toast or throw an exception. This shouldn't happen btw.
                throw t
            }

            override fun onResponse(call: Call<Places>, response: Response<Places>)  {
                Common.currNearByRestaurants = response.body()!!.results
                // This for loop literally sets a marker on every target restaurant on the map.
                for (i in 0 until response.body()!!.results!!.size) {
                    var markerOptions = MarkerOptions()
                    var currPlace = response.body()!!.results!![i]
                    val placeLatLng = LatLng(currPlace.geometry!!.location!!.lat, currPlace.geometry!!.location!!.lng)
                    markerOptions.position(placeLatLng)
                    markerOptions.title(currPlace.name)
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    markerOptions.snippet(i.toString())

                    map!!.addMarker(markerOptions) // Adding a marker on a map literally
                }
            }
        })
    }


    fun getRestaurantDetails(param : String)  {
        Log.e(TAG, param)
        var respond: Call<Restaurant> = service.getRestaurantDetails(param)
        respond.enqueue(object: Callback<Restaurant> {

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                // Toast or throw an exception. This shouldn't happen btw.

                throw t
            }

            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {

                Log.e(TAG, response.body().toString())
                Log.e(TAG, response.body()!!.result!!.name)
                Common.currSelectedRestaurant = response.body()
            }
        })
    }
}