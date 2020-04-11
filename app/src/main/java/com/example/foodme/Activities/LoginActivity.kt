package com.example.foodme.Activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.foodme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

// This class reference: https://www.youtube.com/watch?v=MA8WbvROrLs
class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    private var isLogout: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        btn_sign_up.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
            finish()
        }

        btn_log_in.setOnClickListener {
            doLogin()
        }
        updateUI(auth.currentUser)
    }

    fun doLogin() {
        if (tv_email_login.text.toString().isEmpty()) {
            tv_email_login.error = "Please enter email"
            tv_email_login.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_email_login.text.toString()).matches()) {
            tv_email_login.error = "Please enter valid email"
            tv_email_login.requestFocus()
            return
        }

        if (tv_password.text.toString().isEmpty()) {
            tv_password.error = "Please enter password"
            tv_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(tv_email_login.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println("a")

                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        println("b")
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if (document.get("email").toString().equals(currentUser!!.email)) {
                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("email", document.get("email").toString())
                                intent.putExtra("count", 0)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        println("throw an exception")
                    }
            } else {
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}