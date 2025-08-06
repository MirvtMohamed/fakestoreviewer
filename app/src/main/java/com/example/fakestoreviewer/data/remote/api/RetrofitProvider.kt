package com.example.fakestoreviewer.data.remote.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitProvider {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.escuelajs.co/api/v1/")
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .build()

    val api = retrofit.create(ProductApi::class.java)

}