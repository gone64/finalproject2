package com.example.finalproject2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecAdapter(private val context: Context,private val photos : List<PhotoClass>) : RecyclerView.Adapter<PhotoViewHolder> (){
    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.model,parent,false)

        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.photoName.text = photos[position].namee
        Glide.with(context)
                .load(photos[position].urll)
                .into(holder.photoImage)
        }
        }




class  PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val photoName : TextView = itemView.findViewById(R.id.rec_name)
    val photoImage : ImageView = itemView.findViewById(R.id.rec_imageview)


}