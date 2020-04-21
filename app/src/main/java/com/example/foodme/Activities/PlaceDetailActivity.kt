package com.example.foodme.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodme.Activities.Network.PlaceRepository
import com.example.foodme.R

class PlaceDetailActivity : AppCompatActivity() {

    // API
    var placeRepository: PlaceRepository = PlaceRepository()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)
    }
}
