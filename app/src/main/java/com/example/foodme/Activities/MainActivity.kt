package com.example.foodme.Activities

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.foodme.Activities.Data.User
import com.example.foodme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recommendation.*

import androidx.lifecycle.ViewModelProviders
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse

class MainActivity : AppCompatActivity() {

    /*
    This activity serves as a dashboard.
    Recommended items and buttons to other activites will be displayed here.
     */

    /* Firebase instances */
    private lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var firestore: FirebaseFirestore

    // Viewmodel for login
    private lateinit var viewModel: MainActivityViewModel

    companion object {
        private const val TAG = "MainActivity"
        private const val RC_SIGN_IN = 9001
        private const val LIMIT = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        firestore = FirebaseFirestore.getInstance()
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }


    override fun onStart() {
        super.onStart()

        if (shouldStartSignIn()) {
            startSignIn()
            return
        }

        // Check if this user has a document associated with themselves
        val currUserEmail : String = auth.currentUser!!.email.toString()
        var docRef = db.collection("users").document(currUserEmail);
        docRef.get().addOnSuccessListener { result ->
            if (!result.exists()) {
                val user = User(currUserEmail, 12, 18)
                db.collection("users").document(currUserEmail).set(user)
            }
        }

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
         val placeHolderFood = "hamburger" // Temporarily added to move to MapsActivity
         val mBuilder = AlertDialog.Builder(this)
            .setMessage("Based on your recommendation, you like " + placeHolderFood) // Temporarily added to move to MapsActivity
            .setView(dialogView)
            .setTitle("Recommendation")
         val mAlertDialog = mBuilder.show()
         mAlertDialog.dia_go.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("recommendedFood", placeHolderFood)
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

    private fun shouldStartSignIn(): Boolean {
        return !viewModel.isSigningIn && auth.currentUser == null
    }

    private fun startSignIn() {
        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders((listOf(AuthUI.IdpConfig.EmailBuilder().build())))
            .setIsSmartLockEnabled(false)
            .build()
        startActivityForResult(intent, RC_SIGN_IN)
        viewModel.isSigningIn = true
    }

    fun logOut() {
        viewModel.isSigningIn = false
        auth.signOut()
        finish()
    }
}
