package com.example.foodme.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*
    This activity serves as a dashboard.
    Recommended items and buttons to other activites will be displayed here.
     */

    /* Firebase instances */
    private lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
    }

    override fun onStart() {
        super.onStart()

        btn_customization.setOnClickListener {
            val intent = Intent(this, CustomizationActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_roulette.setOnClickListener {
            val intent = Intent(this, RouletteActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_gallery.setOnClickListener {
        }

        btn_map.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_logout.setOnClickListener {
            logOut()
        }
    }

    fun logOut() {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
