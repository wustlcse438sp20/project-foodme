package com.example.foodme.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodme.Activities.Common.Common
import com.example.foodme.Activities.Network.PlaceRepository
import com.example.foodme.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_place_detail.*
import android.content.ContentValues.TAG
import android.util.Log

class PlaceDetailActivity : AppCompatActivity() {

    // API
    var placeRepository: PlaceRepository = PlaceRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        if (Common.currSelectedResults!!.name != null) {
            place_name.text= Common.currSelectedResults!!.name.toString()
        } else {
            place_name.text= "Place name not shown"
        }

        if (Common.currSelectedResults!!.formatted_address != null) {
            place_address.text= Common.currSelectedResults!!.formatted_address.toString()
        } else {
            place_address.text= "Address not shown"
        }

        if(Common.currSelectedResults!!.photos != null && Common.currSelectedResults!!.photos!!.size > 0) { // If there is at least one photo ...
            Picasso
                .get()
                .load(getPhotoURL(Common.currSelectedResults!!.photos!![0].photo_reference!!, 1000))
                .into(place_photo)
        } else {
            Log.e(TAG, "No photo associated with this place...")
            Log.e(TAG, Common.currSelectedResults!!.name.toString())
        }

        if (Common.currSelectedResults!!.rating != null) {
            rating_bar.rating = Common.currSelectedResults!!.rating.toFloat()
        } else {
            rating_bar.rating = -1.0f
        }

        if (Common.currSelectedResults!!.opening_hours != null) {
            if (Common.currSelectedResults!!.opening_hours!!.open_now) {
                place_open.text = "Opened"
            } else {
                place_open.text = "Closed"
            }
        } else {
            place_open.text = "Not opened"
        }
    }

    private fun getPhotoURL(photoReference: String, maxWidth: Int): String {
        var url : String = "https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=$maxWidth" +
                "&photoreference=$photoReference" +
                "&key=AIzaSyAnSJQFVgcMDdxb1WzkQwLZM_zLpJNSWU0"
        return url.toString()
    }
}
