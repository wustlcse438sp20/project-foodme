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

    var temp_food = ""

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
//        val currentUser =
//            db.collection("users").whereEqualTo("email", auth.currentUser!!.email)
//        currentUser.get().addOnSuccessListener { documentSnapshot ->
//            var data = documentSnapshot.toObjects(User::class.java)
//        }

        if (auth.currentUser != null) {
            updateFood()
        }

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
                val user = User(currUserEmail, "12:00", "18:00")
                db.collection("users").document(currUserEmail).set(user)
            }
        }


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
            updateFood()

            dialogView()
        }
        //danny added done//

        btn_logout.setOnClickListener {
            logOut()
        }
    }

    private fun updateFood(){
        val currentUser =
            db.collection("users").whereEqualTo("email", auth.currentUser!!.email)
        var temp1 = ""
        var temp2 = ""
        var temp3 = ""
        var temp4 = ""
        var temp5 = ""
        var temp6 = ""
        var temp7 = ""
        var temp8 = ""
        var temp9 = ""
        var temp10 = ""
        var temp11 = ""
        var temp12 = ""
        var temp13 = ""
        var temp14 = ""
        currentUser.get().addOnSuccessListener { documentSnapshot ->
            var data = documentSnapshot.toObjects(User::class.java)
            if(data[0].american_Cusi==true){
                temp1 = "American/ "
            }
            if(data[0].barbeque_Cusi==true){
                temp2 = "Barbeque/ "
            }
            if(data[0].chinese_Cusi==true){
                temp3 = "Chinese/ "
            }
            if(data[0].french_Cusi==true){
                temp4 = "French/ "
            }
            if(data[0].hamburger_Cusi==true){
                temp5 = "Hamburger/ "
            }
            if(data[0].indian_Cusi==true){
                temp6 = "Indian/ "
            }
            if(data[0].italian_Cusi==true){
                temp7 = "Italian/ "
            }
            if(data[0].japanese_Cusi==true){
                temp8 = "Japanese/ "
            }
            if(data[0].mexican_Cusi==true){
                temp9 = "Mexican/ "
            }
            if(data[0].pizza_Cusi==true){
                temp10 = "Pizza/ "
            }
            if(data[0].seafood_Cusi==true){
                temp11 = "Seafood/ "
            }
            if(data[0].steak_Cusi==true){
                temp12 = "Steak/ "
            }
            if(data[0].sushi_Cusi==true){
                temp13 = "Sushi/ "
            }
            if(data[0].thai_Cusi==true){
                temp14 = "Thai/ "
            }
            temp_food = temp1 + temp2 + temp3 +temp4+temp5+temp6+temp7+temp8+temp9+temp10+temp11+temp12+temp13+temp14
        }
    }

     private fun dialogView() {
         val dialogView = LayoutInflater.from(this).inflate(R.layout.recommendation, null)
         updateFood()
         var placeHolderFood = temp_food // Temporarily added to move to MapsActivity
         println(temp_food)
         val mBuilder = AlertDialog.Builder(this)
            .setMessage("You liked these food: " + temp_food + " Do you want to see our food recommendation?")
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
