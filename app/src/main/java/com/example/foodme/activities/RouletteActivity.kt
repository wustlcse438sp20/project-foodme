package com.example.foodme.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodme.activities.Adapter.FoodListAdapter
import com.example.foodme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_roulette.*




class RouletteActivity : AppCompatActivity() {

    private var foodList = ArrayList<String>()
    private var foodListFromDatabase = ArrayList<String>()
    private var listView: ListView? = null

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    val storedFoods : Array<String> = arrayOf("american", "barbeque", "chinese", "french", "hamburger", "indian", "italian", "japanese", "mexican", "pizza", "seafood", "steak", "sushi", "thai")
    val foodNames : Array<String> = arrayOf("American", "Barbecue", "Chinese", "French", "Hamburger", "Indian", "Italian", "Japanese", "Mexican", "Pizza", "Seafood", "Steak", "Sushi", "Thai")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val intent : Intent? = intent
        if (intent!!.hasExtra("foodList")){
            foodList = intent!!.getStringArrayListExtra("foodList")
        }
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        getInitial()
    }

    override fun onStart() {
        super.onStart()
        uncheck.setOnClickListener {
            uncheckAll()
        }
        val email = auth.currentUser!!.email.toString()
        val readPref = db.collection("users").document(email)
        readPref.get().addOnSuccessListener { document ->
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            for (i in 0..storedFoods.size-1){
                if (document.get(storedFoods[i] + "_Cusi") == true){
                    val findName = "checkBox_" + storedFoods[i]
                    findViewById<CheckBox>(resources.getIdentifier(findName ,"id",  baseContext.packageName)).isChecked= true
                }
            }
        }
        backToMain.setOnClickListener {
            toMain()
        }
    }

    private fun uncheckAll(){
        for (i in 0..storedFoods.size-1){
            val findName = "checkBox_" + storedFoods[i]
            findViewById<CheckBox>(resources.getIdentifier(findName ,"id",  baseContext.packageName)).isChecked= false;
        }
    }

    private fun getInitial(){
        listView = foods
        val adapter = FoodListAdapter(this, foodList)
        listView?.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    fun addFood(view: View?){
        val imm: InputMethodManager = baseContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        if (foodNameText.text != null){
            foodList.add(foodNameText.text.toString())
            val toast = Toast.makeText(this, "Food Added", Toast.LENGTH_SHORT)
            toast.show()
            (listView?.adapter as? FoodListAdapter)?.notifyDataSetChanged()
        }
        else{
            val toast = Toast.makeText(this, "Invalid Food Name", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun deleteFood(position: Int) {
        foodList.removeAt(position)
        val toast = Toast.makeText(this, "Food Deleted", Toast.LENGTH_SHORT)
        toast.show()
        (listView?.adapter as? FoodListAdapter)?.notifyDataSetChanged()
    }

    fun toRoll(view: View?){
        for (i in 0..storedFoods.size-1){
            val findName = "checkBox_" + storedFoods[i]
            if (findViewById<CheckBox>(resources.getIdentifier(findName ,"id",  baseContext.packageName)).isChecked){
                foodListFromDatabase.add(foodNames[i])
            }
        }
        if (foodListFromDatabase.isEmpty() && foodList.isEmpty()){
            val toast = Toast.makeText(this, "Add Food Before Rolling", Toast.LENGTH_SHORT)
            toast.show()
        }
        else{
            val toRollIntent = Intent(this, RollActivity::class.java)
            toRollIntent.putExtra("foodList", foodList)
            toRollIntent.putExtra("foodListFromDatabase", foodListFromDatabase)
            startActivity(toRollIntent)
            finish()
        }
    }

    fun toMain(){
        val toMainIntent = Intent(this, MainActivity::class.java)
        startActivity(toMainIntent)
        finish()
    }


}
