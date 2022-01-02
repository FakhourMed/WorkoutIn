package com.fakhour.workoutin.common.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


const val  BASE_URL="https://www.strava.com/api/v3/"

object RetrofitInstance {

    val token="f91278ad5c9b9ba480135c28a524393b8a7d498f "
    private val retrofit by lazy {
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return chain.proceed(newRequest)
            }
        }).build()

      Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api :StarvaApi by lazy{
        retrofit.create(StarvaApi::class.java)
    }

}


