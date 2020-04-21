package com.example.foodme.Activities

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodme.Activities.Data.User
import com.example.foodme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_customization.*
import java.text.SimpleDateFormat
import java.util.*


class CustomizationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customization)
        //firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // lunch
        val pickLunchTimeBtn = findViewById<Button>(R.id.lunch_time)
        val lunchTextView = findViewById<TextView>(R.id.lunch_txt)
        pickLunchTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                lunchTextView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        // dinner
        val pickDinTimeBtn = findViewById<Button>(R.id.din_time)
        val dinTextView     = findViewById<TextView>(R.id.din_txt)
        pickDinTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                dinTextView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //checkbox
        val checkbox_1 = findViewById<CheckBox>(R.id.checkBox_ame)

        checkbox_1?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " American Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("american_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("american_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_2 = findViewById<CheckBox>(R.id.checkBox_bar)
        checkbox_2?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Barbecue cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("barbeque_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("barbeque_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_3 = findViewById<CheckBox>(R.id.checkBox_chi)
        checkbox_3?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Chinese Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("chinese_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("chinese_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_4 = findViewById<CheckBox>(R.id.checkBox_French)
        checkbox_4?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " French Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("french_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("french_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_5 = findViewById<CheckBox>(R.id.checkBox_ham)
        checkbox_5?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " hamburger cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("hamburger_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("hamburger_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_6 = findViewById<CheckBox>(R.id.checkBox_ind)
        checkbox_6?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Indian Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("indian_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("indian_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_7 = findViewById<CheckBox>(R.id.checkBox_itl)
        checkbox_7?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Italian Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("italian_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("italian_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_8 = findViewById<CheckBox>(R.id.checkBox_jap)
        checkbox_8?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + "Japanese Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("japanese_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("japanese_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_9 = findViewById<CheckBox>(R.id.checkBox_mex)
        checkbox_9?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Mexican Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("mexican_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("mexican_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_10 = findViewById<CheckBox>(R.id.checkBox_piz)
        checkbox_10?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Pizza Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("pizza_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("pizza_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_11 = findViewById<CheckBox>(R.id.checkBox_sea)
        checkbox_11?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Seafood Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("seafood_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("seafood_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_12 = findViewById<CheckBox>(R.id.checkBox_stk)
        checkbox_12?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Steak Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("steak_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("steak_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_13 = findViewById<CheckBox>(R.id.checkBox_sus)
        checkbox_13?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Sushi Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("sushi_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("sushi_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_14 = findViewById<CheckBox>(R.id.checkBox_thai)
        checkbox_14?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Thai Cuisine."
            var email = auth.currentUser!!.email.toString()
            if(isChecked) {
                db.collection("users").document(email).update("thai_Cusi", true).addOnSuccessListener {
                }
            }else{
                db.collection("users").document(email).update("thai_Cusi", false).addOnSuccessListener {
                }
            }
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }



        cus_back.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // need to send data
        cus_save.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }




}
