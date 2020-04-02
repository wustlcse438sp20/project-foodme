package com.example.foodme.Activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.foodme.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_register.*
import java.util.HashMap

// This class reference: https://www.youtube.com/watch?v=MA8WbvROrLs
class RegisterActivity : AppCompatActivity() {
    lateinit var db : FirebaseFirestore

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        db = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()
        btn_sign_up.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        if (tv_email.text.toString().isEmpty() || tv_nickname.toString().isEmpty()) {
            tv_email.error = "Please enter email or nickname"
            tv_email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_email.text.toString()).matches()) {
            tv_email.error = "Please enter valid email"
            tv_email.requestFocus()
            return
        }

        if (tv_password.text.toString().isEmpty()) {
            tv_password.error = "Please enter password"
            tv_password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(tv_email.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userInfoMap: MutableMap<String, Any> = HashMap()
                                userInfoMap["email"] = tv_email.text.toString().toLowerCase()
                                userInfoMap["nickname"] = tv_nickname.text.toString()
                                userInfoMap["win"] = 0
                                userInfoMap["lose"] = 0
                                userInfoMap["chips"] = 1000 // I am not sure about the default amount of chips for a newly registered player. I set it as 1,000 for now.
                                AddUserIntoDB(userInfoMap)

                                startActivity(Intent(this,
                                    LoginActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Add a newly registered user into the Firebase DB
    private fun AddUserIntoDB(userInfoMap: MutableMap<String, Any>) {
        db.collection("users")
            .add(userInfoMap)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Toast.makeText(this, "Exercise created", Toast.LENGTH_LONG)
            })
            .addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this, "Failed to insert data!", Toast.LENGTH_LONG)
            })
    }
}
