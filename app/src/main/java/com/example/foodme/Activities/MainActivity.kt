package com.example.foodme.Activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.foodme.Activities.Data.User
import com.example.foodme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recommendation.*

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

        val currentUser =
            db.collection("users").whereEqualTo("email", auth.currentUser!!.email)
        currentUser.get().addOnSuccessListener { documentSnapshot ->
            var data = documentSnapshot.toObjects(User::class.java)
        }
    }

    override fun onStart() {
        super.onStart()

        preference()
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

        // Danny added//
        btn_rec.setOnClickListener{
            dialogView()
        }
        //danny added done//

        btn_map.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_logout.setOnClickListener {
            logOut()
        }
    }

    fun preference(){
        var email = auth.currentUser!!.email.toString()
        val sample = db.collection("users").whereEqualTo("american_Cusi",true)

        test_test.text = " hi " + sample
    }

     private fun dialogView() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.recommendation, null)
        val mBuilder = AlertDialog.Builder(this)
            .setMessage("Based on your recommendation, you like")
            .setView(dialogView)
            .setTitle("Recommendation")
        val mAlertDialog = mBuilder.show()
         mAlertDialog.dia_go.setOnClickListener {
            val intent = Intent(this, RecommendationActivity::class.java)
            startActivity(intent)
            mAlertDialog.dismiss()
        }
         mAlertDialog.dia_cust.setOnClickListener {
             val intent = Intent(this, CustomizationActivity::class.java)
             startActivity(intent)
             mAlertDialog.dismiss()
         }

         mAlertDialog.dia_back.setOnClickListener {
            mAlertDialog.dismiss()
        }
        }


    fun logOut() {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
