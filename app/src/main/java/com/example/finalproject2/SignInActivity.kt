package com.example.finalproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var sgn_mail : EditText
    private lateinit var sgn_pass : EditText
    private lateinit var sgn_sgnin : TextView
    private lateinit var sgn_sgnup : TextView
    private lateinit var auth : FirebaseAuth
    private lateinit var forgot : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        sgn_mail = findViewById(R.id.sgn_sgnin)
        sgn_pass = findViewById(R.id.editText)
        sgn_sgnin = findViewById(R.id.sgn_txtsgnin)
        sgn_sgnup = findViewById(R.id.sgn_up)
        forgot = findViewById(R.id.frgtpswr)
        forgot.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }
        auth = Firebase.auth
        sgn_sgnup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        sgn_sgnin.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val mail = sgn_mail.text.toString()
        val passw = sgn_pass.text.toString()
        if (mail.isEmpty()){
            sgn_mail.error = "Enter Mail"
        }
        if (passw.isEmpty()){
            sgn_pass.error = "Enter Password"
        }
        if (mail.isNotEmpty() && passw.isNotEmpty()){
            auth.signInWithEmailAndPassword(mail,passw).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,ViewPageActivity::class.java))
                }else{
                    Toast.makeText(this, "Login Failed Check Mail And Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}