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
import com.example.foodme.R
import kotlinx.android.synthetic.main.activity_customization.*
import java.text.SimpleDateFormat
import java.util.*


class CustomizationActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customization)
        // lunch
        val pickLunchTimeBtn = findViewById<Button>(R.id.lunch_time)
        val lunchTextView     = findViewById<TextView>(R.id.lunch_txt)
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
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_2 = findViewById<CheckBox>(R.id.checkBox_bar)
        checkbox_2?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Barbecue cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_3 = findViewById<CheckBox>(R.id.checkBox_chi)
        checkbox_3?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Chinese Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_4 = findViewById<CheckBox>(R.id.checkBox_French)
        checkbox_4?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " French Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_5 = findViewById<CheckBox>(R.id.checkBox_ham)
        checkbox_5?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " hamburger cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_6 = findViewById<CheckBox>(R.id.checkBox_ind)
        checkbox_6?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Indian Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_7 = findViewById<CheckBox>(R.id.checkBox_itl)
        checkbox_7?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Italian Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_8 = findViewById<CheckBox>(R.id.checkBox_jap)
        checkbox_8?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + "Japanese Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_9 = findViewById<CheckBox>(R.id.checkBox_mex)
        checkbox_9?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Mexican Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_10 = findViewById<CheckBox>(R.id.checkBox_piz)
        checkbox_10?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Pizza Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_11 = findViewById<CheckBox>(R.id.checkBox_sea)
        checkbox_11?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Seafood Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_12 = findViewById<CheckBox>(R.id.checkBox_stk)
        checkbox_12?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Steak Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_13 = findViewById<CheckBox>(R.id.checkBox_sus)
        checkbox_13?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Sushi Cuisine."
            Toast.makeText(this@CustomizationActivity, msg, Toast.LENGTH_SHORT).show()
        }
        val checkbox_14 = findViewById<CheckBox>(R.id.checkBox_thai)
        checkbox_14?.setOnCheckedChangeListener { buttonView, isChecked ->
            val msg = "You have " + (if (isChecked) "checked" else "unchecked") + " Thai Cuisine."
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
