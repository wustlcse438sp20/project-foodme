package com.example.foodme.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodme.Activities.Common.Common
import com.example.foodme.Activities.Data.Restaurant
import com.example.foodme.Activities.Data.Results
import com.example.foodme.Activities.Network.PlaceRepository
import com.example.foodme.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_place_detail.*

class PlaceDetailActivity : AppCompatActivity() {

    // API
    var placeRepository: PlaceRepository = PlaceRepository()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)


        Common.currSelectedResults
        place_name.text=""
        place_address.text=""
        place_open_hours.text=""

        var currRestaurant : Restaurant?= null

        btn_show_map.setOnClickListener {   // Change this to "I will eat it !"
            // Then add a logic to add this record in user db
        }

        if(Common.currSelectedResults!!.photos != null && Common.currSelectedResults!!.photos!!.size > 0) { // If there is at least one photo ...
            Picasso.get()
                .load(getPhotoOfPlace(Common.currSelectedResults!!.photos!![0].photo_reference!!, 1000))
                .into(photo)
        }

        if (Common.currSelectedResults!!.rating != null) {
            rating_bar.rating = Common.currSelectedResults!!.rating.toFloat()
        } else {
            rating_bar.rating = -1.0f
        }

        if (Common.currSelectedResults!!.opening_hours != null) {
            place_open_hours.text = "Open :" + Common.currSelectedResults!!.opening_hours!!.open_now
        } else {
            place_open_hours.text = "Not opened"
        }
        placeRepository.getRestaurantDetails(getPlaceDetailUrl(Common.currSelectedResults!!.place_id!!))

        /*

        .run {

            place_address.text = Common.currSelectedRestaurant!!.result!!.formatted_address
            place_name.text = Common.currSelectedRestaurant!!.result!!.name

        }
         */
    }

    private fun getPlaceDetailUrl(placeId: String): String {
        var url : String = "https://maps.googleapis.com/maps/api/place/details/json?" +
                "placeid=$placeId" +
                "&key=AIzaSyAnSJQFVgcMDdxb1WzkQwLZM_zLpJNSWU0"
        return url.toString()
    }

    private fun getPhotoOfPlace(photoReference: String, maxWidrth: Int): String {
        var url : String = "https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=$maxWidrth" +
                "photoreference=$photoReference" +
                "&key=AIzaSyAnSJQFVgcMDdxb1WzkQwLZM_zLpJNSWU0"
        return url.toString()

    }
}
