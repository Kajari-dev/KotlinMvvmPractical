package com.example.kotlinmvvmpractical.data

import android.provider.Contacts.Photos
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.kotlinmvvmpractical.api.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersDataSource : PageKeyedDataSource<Long?, UserResponse?>() {
    lateinit var dataService: RetrofitApi
    override fun loadInitial(
        params: LoadInitialParams<Long?>,
        callback: LoadInitialCallback<Long?, UserResponse?>
    ) {
        dataService = RetrofitApi.create()
        val data: Call<List<UserResponse>> = dataService.getUserData(10,10)
        data.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                val userList: List<UserResponse>? = response.body()
                if (userList != null) {
                    callback.onResult(userList, null, 2.toLong())
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.d("Failure",t.message)
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Long?>,
        callback: LoadCallback<Long?, UserResponse?>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Long?>,
        callback: LoadCallback<Long?, UserResponse?>) {
        dataService = RetrofitApi.create()
        val data: Call<List<UserResponse>> = dataService.getUserData(10,10)
        data.enqueue(object : Callback<List<UserResponse>> {

            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                val userList: List<UserResponse>? = response.body()
                if (userList != null) {
                    callback.onResult(userList, (params.key.toLong() + 1))
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.d("Failure",t.message)
            }
        })
    }
}