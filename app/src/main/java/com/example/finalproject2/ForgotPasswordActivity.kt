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

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var mail : EditText
    private lateinit var reset : TextView
    private lateinit var auth : FirebaseAuth
    private lateinit var back : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)
        mail = findViewById(R.id.Mail)
        reset = findViewById(R.id.request)
        back = findViewById(R.id.back_login)

        reset.setOnClickListener {
            val maill = mail.text.toString()
            auth = Firebase.auth
            if (maill.isEmpty()){
                mail.error = "Enter Email"
            }
            auth.sendPasswordResetEmail(maill).addOnCompleteListener{
                if (it.isSuccessful){
                    mail.text.clear()
                    Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

            }
        }
        back.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            mail.text.clear()
        }


    }
}