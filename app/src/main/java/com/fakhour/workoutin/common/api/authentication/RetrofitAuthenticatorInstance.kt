package com.fakhour.workoutin.common.api.authentication

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


const val  BASE_URL_AUTHENTICATOR="https://www.strava.com/oauth/"
const val  FIRST_GRANT_TYPE="authorization_code"

object RetrofitAuthenticatorInstance {

    private val retrofit by lazy {
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .build()
                return chain.proceed(newRequest)
            }
        }).build()

      Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL_AUTHENTICATOR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiAuthenticator :StarvaApiAuthenticator by lazy{
        retrofit.create(StarvaApiAuthenticator::class.java)
    }

}


