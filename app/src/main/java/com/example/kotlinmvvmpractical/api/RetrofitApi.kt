package com.example.kotlinmvvmpractical.api

import com.example.kotlinmvvmpractical.data.Data
import com.example.kotlinmvvmpractical.data.UserResponse
import com.example.kotlinmvvmpractical.data.UsersItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("api/users")
    fun getUserData(@Query("offset") offset :Int,@Query("limit") limit :Int): Call<List<UserResponse>>

    companion object {
        private const val BASE_URL = "http://sd2-hiring.herokuapp.com/"
        fun create(): RetrofitApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi::class.java)
        }
    }
}