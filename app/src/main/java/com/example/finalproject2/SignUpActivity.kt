package com.example.finalproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var mail : EditText
    private lateinit var pass : EditText
    private lateinit var number : EditText
    private lateinit var sgnup : TextView
    private lateinit var sgnin : TextView
    private lateinit var dbref : DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mail = findViewById(R.id.up_mail)
        pass = findViewById(R.id.up_pass)
        number = findViewById(R.id.up_number)
        sgnup = findViewById(R.id.up_sgnup)
        sgnin = findViewById(R.id.up_sgnin)
        auth = Firebase.auth
        dbref = FirebaseDatabase.getInstance().getReference("Users")
        sgnup.setOnClickListener {
            createAcc()
        }
        sgnin.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
    }

    private fun createAcc() {
        val maill = mail.text.toString()
        val passs = pass.text.toString()
        val numberr = number.text.toString()
        if (maill.isEmpty()) {
            mail.error = "Enter Mail"
        }else if (!maill.contains("@")){
            mail.error = "Mail Is Not Valid"
        }
        if (passs.isEmpty()) {
            pass.error = "Enter Password"
        }else if (passs.length < 8 && passs.isNotEmpty()){
            pass.error = "Pass Length Is Not 8 Symbol"
        }
        if (numberr.isEmpty()) {
            number.error = "Enter Phone Number"
        }else if (!numberr.startsWith("5")) {
            number.error = "Number Doesn't Start With 5"
        }else if (numberr.length != 9) {
            number.error = "Number Is Not 9 Symbols lenght"
        }




        if (maill.contains("@") && maill.isNotEmpty() && passs.isNotEmpty() && numberr.isNotEmpty() && numberr.startsWith("5") && numberr.length == 9) {
            auth.createUserWithEmailAndPassword(maill, passs).addOnCompleteListener() {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
            }
            val userId = dbref.push().key!!
            val user = UserModel(userId, maill, passs, numberr)
            dbref.child(userId).setValue(user).addOnCompleteListener {
                mail.text.clear()
                pass.text.clear()
                number.text.clear()

            }.addOnFailureListener {

            }
        }
    }
}
