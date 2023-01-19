package com.example.finalproject2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PhotoAddFragment : Fragment(R.layout.fragment_photo_add) {
    private lateinit var link : EditText
    private lateinit var check : TextView
    private lateinit var addphoto : TextView
    private lateinit var name : EditText
    private lateinit var dbref : DatabaseReference
    private lateinit var gallery : TextView
    private lateinit var image : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        link = view.findViewById(R.id.url)
        check = view.findViewById(R.id.ckeck)
        addphoto = view.findViewById(R.id.addphoto)
        name = view.findViewById(R.id.editText2)
        dbref = FirebaseDatabase.getInstance().getReference("Gallery")
        gallery = view.findViewById(R.id.gallery)
        image = view.findViewById(R.id.imageview)
        check = view.findViewById(R.id.ckeck)
        check.setOnClickListener {
            val linkk = link.text.toString()
            val nameee = name.text.toString()
            if (linkk.isNotEmpty() && nameee.isNotEmpty()){
                Glide.with(view).load(linkk).into(image)
            }else if(linkk.isEmpty()){
                link.error = "Enter Photo Link"
            }else if (nameee.isEmpty()){
                name.error = "Enter Name Of Photo"
            }

        }


        gallery.setOnClickListener {
            startActivity(Intent(context,GalleryActivity::class.java))
        }

        addphoto.setOnClickListener {
            savePhotoData()
        }
    }

    private fun savePhotoData() {
        val urll = link.text.toString()
        val namee = name.text.toString()
        val photoId = dbref.push().key!!
        val photo = PhotoModel(urll,namee,photoId)
        if(urll.isNotEmpty() && namee.isNotEmpty()){
            dbref.child(photoId).setValue(photo).addOnCompleteListener{
                link.text.clear()
                name.text.clear()

            }
        }
    }


}