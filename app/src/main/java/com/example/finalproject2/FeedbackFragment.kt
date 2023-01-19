package com.example.finalproject2


import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*


class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private lateinit var feedbackk : EditText
    private lateinit var ratee : EditText
    private lateinit var result : TextView
    private lateinit var dbRef : DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedbackk = view.findViewById(R.id.feedback)
        ratee = view.findViewById(R.id.score)
        result = view.findViewById(R.id.AddResult)
        dbRef = FirebaseDatabase.getInstance().getReference("Rates")
        result.setOnClickListener {
            saveFeedbackData()
        }
    }

    private fun saveFeedbackData() {
        val Feedback = feedbackk.text.toString()
        val Rate = ratee.text.toString().toInt()
        val Id = dbRef.push().key!!
        val back = FeedbackModel(Rate,Feedback, Id)
        if (Feedback.isNotEmpty() && Rate >= 1 && Rate<=10){
            dbRef.child(Id).setValue(back).addOnCompleteListener{
                feedbackk.text.clear()
                ratee.text.clear()
            }}else if (Rate >10 ){
                ratee.error = "Rate Must be Less or Equal Than 10"
        }else if (Rate < 1 ){
            ratee.error = "Rate Must Be Higher or Equal Than 1"
        }




    }
}


