package com.example.finalproject2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalproject2.databinding.ActivityGalleryBinding
import com.google.firebase.database.*

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGalleryBinding
    private lateinit var dataList : ArrayList<PhotoClass>
    private lateinit var adapter: RecAdapter
    var databaseReference : DatabaseReference? = null
    var eventListener : ValueEventListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gridLayoutManager = GridLayoutManager(this@GalleryActivity,1)
        binding.recycler1.layoutManager = gridLayoutManager
        dataList = ArrayList()
        adapter = RecAdapter(this@GalleryActivity,dataList)
        binding.recycler1.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Gallery")
        eventListener = databaseReference!!.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (photoSnapshot in snapshot.children){
                    val dataClass = photoSnapshot.getValue(PhotoClass::class.java)
                    if (dataClass != null){
                        dataList.add(dataClass)
                    }
                }
                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}