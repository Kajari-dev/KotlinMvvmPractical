package com.example.kotlinmvvmpractical.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.kotlinmvvmpractical.R
import com.example.kotlinmvvmpractical.data.Data
import com.example.kotlinmvvmpractical.data.UserResponse
import com.example.kotlinmvvmpractical.data.UsersItem

class UserAdapter : PagedListAdapter<UsersItem, UserAdapter.PhotoViewHolder?>(UserResponse.CALLBACK) {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view: View = layoutInflater.inflate(R.layout.user_list_item, viewGroup, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(
        photoViewHolder: PhotoViewHolder,
        i: Int
    ) {
        photoViewHolder.tvUserName.text= getItem(i)?.name
        Glide.with(photoViewHolder.itemView.context).load(getItem(i)?.image)
            .into(photoViewHolder.ivPhoto)
        if(i % 2==0)
            photoViewHolder.rvUserImagesList.layoutManager=GridLayoutManager(photoViewHolder.itemView.context,2)
        else
            photoViewHolder.rvUserImagesList.layoutManager=GridLayoutManager(photoViewHolder.itemView.context,1)

        photoViewHolder.rvUserImagesList.adapter=UserImageListAdapter(getItem(i)?.items)
    }

    inner class PhotoViewHolder(itemView: View) : ViewHolder(itemView) {
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivUserImage)
        var tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        var rvUserImagesList:RecyclerView=itemView.findViewById(R.id.rvGrid)

    }
}