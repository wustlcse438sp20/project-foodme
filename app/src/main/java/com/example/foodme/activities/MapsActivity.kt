package com.example.foodme.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.foodme.activities.Data.Results
import com.example.foodme.activities.Network.PlaceRepository
import com.example.foodme.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

import com.example.foodme.activities.Common.Common
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // Map and location information objects
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private var homeLat : Double? = null
    private var homeLong : Double? = null
    private var homeAddress : String = "1600 Amphitheatre Pkwy, Mountain View, CA 94043, United States"
    private lateinit var foodToRecommend : String
    private var nearByRestaurantsArr : Array<Results>?=null

    // API
    var placeRepository: PlaceRepository = PlaceRepository()

    // PERMISSION CONSTANT
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        //set the map and client location
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()

        foodToRecommend = intent.getStringExtra("recommendedFood")

    }

    //check the permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        //set the click longitude
        setMapLongClick(map)

        //set the user location
        enableMyLocation()

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.showRestaurantsButton -> nearByRestaurants(foodToRecommend)
                R.id.backButton -> goBackActivity()
            }
            true
        }

        map!!.setOnMarkerClickListener { marker ->
            Common.currSelectedResults = Common.currNearByRestaurants!![Integer.parseInt(marker.snippet)]
            val intent = Intent(this@MapsActivity, PlaceDetailActivity::class.java)
            startActivity(intent)
            true
        }
    }


    //returns if the needed permissions are available
    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }


    //click listener for additional locations
    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            )
        }
    }

    //sets the home location
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true

            //set the location to the home location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    //store the location values
                    homeLat =  location?.latitude as Double
                    homeLong = location?.longitude as Double

                    val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
                    val list = geocoder.getFromLocation(homeLat as Double, homeLong as Double, 1)
                    homeAddress = list[0].getAddressLine(0)

                    //set the location and zoom variables
                    val homeLatLng = LatLng(homeLat as Double, homeLong as Double)
                    val zoomLevel = 13f

                    //move the camera and set a marker
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                    map.addMarker(MarkerOptions().position(homeLatLng))
                }
        }
        else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun nearByRestaurants(typePlace: String) {
        map.clear()
        var url : String = "https://maps.googleapis.com/maps/api/place/textsearch/json?" +
                "query=$typePlace" +
                "+in+$homeAddress" +
                "&key=AIzaSyAnSJQFVgcMDdxb1WzkQwLZM_zLpJNSWU0"
        placeRepository.getNearbyRestaurants(map,typePlace, url)
    }

    private fun goBackActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
