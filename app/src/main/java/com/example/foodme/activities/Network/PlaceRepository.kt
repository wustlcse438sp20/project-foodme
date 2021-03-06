package com.example.foodme.activities.Network

import android.content.ContentValues.TAG
import android.util.Log
import com.example.foodme.activities.Common.Common
import com.example.foodme.activities.Data.Places
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
}