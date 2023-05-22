package com.example.composecrypto.service

import com.example.composecrypto.BuildConfig
import com.example.composecrypto.model.Crypto
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("coins?apiKey=${BuildConfig.API_KEY}")
    fun  getData(): Call<Crypto>
}