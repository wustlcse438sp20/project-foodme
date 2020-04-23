package com.example.foodme.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.foodme.R
import kotlinx.android.synthetic.main.activity_roll.*

class RollActivity : AppCompatActivity() {

    private var foodList = ArrayList<String>()
    private var foodListFromDatabase = ArrayList<String>()

    private var combinedList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll)


        val intent : Intent? = intent
        if (intent!!.hasExtra("foodList")){
            foodList = intent!!.getStringArrayListExtra("foodList")
        }
        if (intent!!.hasExtra("foodListFromDatabase")){
            foodListFromDatabase = intent!!.getStringArrayListExtra("foodListFromDatabase")
        }

        combinedList.addAll(foodList)
        combinedList.addAll(foodListFromDatabase)

    }

    override fun onStart() {
        super.onStart()
        roll(null)
    }

    fun toRoulette(view : View?){
        val intent = Intent(this, RouletteActivity::class.java)
        intent.putExtra("foodList", foodList)
        startActivity(intent)
        finish()
    }

    fun roll(view : View?){
        rolling.text = "Rolling...."
        var rand = (0..combinedList.size-1).random()
        result.text = combinedList.get(rand)
        rolling.text = "Rolled!"
    }
}
