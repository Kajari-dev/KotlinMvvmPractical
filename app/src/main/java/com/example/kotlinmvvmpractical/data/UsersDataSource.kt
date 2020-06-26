package com.example.kotlinmvvmpractical.data

import android.provider.Contacts.Photos
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.kotlinmvvmpractical.api.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersDataSource : PageKeyedDataSource<Int?, UsersItem?>() {
    lateinit var dataService: RetrofitApi
    override fun loadInitial(
        params: LoadInitialParams<Int?>,
        callback: LoadInitialCallback<Int?, UsersItem?>
    ) {
        dataService = RetrofitApi.create()
        val data: Call<UserResponse> = dataService.getUserData(10,10)
        data.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val userList: List<UsersItem>? = response.body()?.data?.users as List<UsersItem>?
                if (userList != null) {
                    callback.onResult(userList, null, 2)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Failure",t.message)
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int?>,
        callback: LoadCallback<Int?, UsersItem?>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Int?>,
        callback: LoadCallback<Int?, UsersItem?>) {
        dataService = RetrofitApi.create()
        val data: Call<UserResponse> = dataService.getUserData(10,10)
        data.enqueue(object : Callback<UserResponse> {

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val userList: List<UsersItem>? = response.body()?.data?.users as List<UsersItem>
                if (userList != null) {
                    callback.onResult(userList, (params.key + 1))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Failure",t.message)
            }
        })
    }
}