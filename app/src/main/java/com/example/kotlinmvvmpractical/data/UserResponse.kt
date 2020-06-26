package com.example.kotlinmvvmpractical.data

import android.provider.Contacts.Photos
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class UserResponse(
	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: Any? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
):Serializable{
	companion object {
		val CALLBACK: DiffUtil.ItemCallback<UsersItem?> = object : DiffUtil.ItemCallback<UsersItem?>() {
			override fun areItemsTheSame(usersItem: UsersItem, t1: UsersItem): Boolean {
				return usersItem === t1
			}

			override fun areContentsTheSame(usersItem: UsersItem, t1: UsersItem): Boolean {
				return true
			}
		}
	}
}

data class UsersItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("items")
	val items: List<String?>? = null
)

data class Data(

	@field:SerializedName("has_more")
	val hasMore: Boolean? = null,

	@field:SerializedName("users")
	val users: List<UsersItem?>? = null
)
