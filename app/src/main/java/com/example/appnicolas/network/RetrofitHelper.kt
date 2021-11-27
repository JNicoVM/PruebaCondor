package com.example.appnicolas.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
     fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}