package com.example.foodme.Activities

import MyAlarmReceiver
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.foodme.Activities.Data.User
import com.example.foodme.R
import com.firebase.ui.auth.AuthUI
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
    lateinit var firestore: FirebaseFirestore
    var temp_food = ""
    private var fav_Food = ArrayList<String>()


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
        if (auth.currentUser != null) {
            if(fav_Food.size!=0) {

                updateFood()
            }
        }
        if (shouldStartSignIn()) {
            startSignIn()
            return
        }

        // Check if this user has a document associated with themselves
        val currUserEmail : String = auth.currentUser!!.email.toString()
        var docRef = db.collection("users").document(currUserEmail);
        docRef.get().addOnSuccessListener { result ->
            if (!result.exists()) {
                val user = User(currUserEmail, 12, 0,18,0)
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

            dialogView()
        }
        //danny added done//

        btn_logout.setOnClickListener {
            logOut()
        }
    }

    private fun updateFood() {
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
            if (data[0].american_Cusi == true) {
                temp1 = "American"
                fav_Food.add(temp1)
                temp1 = "American/ "
            }
            if (data[0].barbeque_Cusi == true) {
                temp2 = "Barbeque"
                fav_Food.add(temp2)
                temp2 = "Barbeque/ "
            }
            if (data[0].chinese_Cusi == true) {
                temp3 = "Chinese"
                fav_Food.add(temp3)
                temp3 = "Chinese/ "
            }
            if (data[0].french_Cusi == true) {
                temp4 = "French"
                fav_Food.add(temp4)
                temp4 = "French/ "
            }
            if (data[0].hamburger_Cusi == true) {
                temp5 = "Hamburger"
                fav_Food.add(temp5)
                temp5 = "Hamburger/ "
            }
            if (data[0].indian_Cusi == true) {
                temp6 = "Indian"
                fav_Food.add(temp6)
                temp6 = "Indian/ "
            }
            if (data[0].italian_Cusi == true) {
                temp7 = "Italian"
                fav_Food.add(temp7)
                temp7 = "Italian/ "
            }
            if (data[0].japanese_Cusi == true) {
                temp8 = "Japanese"
                fav_Food.add(temp8)
                temp8 = "Japanese/ "
            }
            if (data[0].mexican_Cusi == true) {
                temp9 = "Mexican"
                fav_Food.add(temp9)
                temp9 = "Mexican/ "

            }
            if (data[0].pizza_Cusi == true) {
                temp10 = "Pizza"
                fav_Food.add(temp10)
                temp10 = "Pizza/ "

            }
            if (data[0].seafood_Cusi == true) {
                temp11 = "Seafood"
                fav_Food.add(temp11)
                temp11 = "Seafood/ "

            }
            if (data[0].steak_Cusi == true) {
                temp12 = "Steak"
                fav_Food.add(temp12)
                temp12 = "Steak/ "

            }
            if (data[0].sushi_Cusi == true) {
                temp13 = "Sushi"
                fav_Food.add(temp13)
                temp13 = "Sushi/ "

            }
            if (data[0].thai_Cusi == true) {
                temp14 = "Thai"
                fav_Food.add(temp14)
                temp14 = "Thai/ "

            }
            temp_food =
                temp1 + temp2 + temp3 + temp4 + temp5 + temp6 + temp7 + temp8 + temp9 + temp10 + temp11 + temp12 + temp13 + temp14
        }
    }

     private fun dialogView() {
         val dialogView = LayoutInflater.from(this).inflate(R.layout.recommendation, null)
         updateFood()
         var rand = 0
         if(fav_Food.size!=0) {
             var rand = (0..fav_Food.size - 1).random()
             println(fav_Food.get(rand))
         }
         val placeHolderFood = "hamburger" // Temporarily added to move to MapsActivity
         val mBuilder = AlertDialog.Builder(this)
            .setMessage("Based on your recommendation, you like " + temp_food + " and we recommend you to eat " + placeHolderFood + " food."+ "Do you want to see our recommended restaurants?") // Temporarily added to move to MapsActivity
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
