package com.example.kotlinmvvmpractical.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinmvvmpractical.R
import com.example.kotlinmvvmpractical.data.UsersItem

class UserImageListAdapter(var list: List<String?>?) : RecyclerView.Adapter<UserImageListAdapter.UserPhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.user_image_list_item, parent, false)
        return UserPhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: UserPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(list?.get(position))
            .into(holder.ivPhoto)
    }


    inner class UserPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivImageList)


    }
}