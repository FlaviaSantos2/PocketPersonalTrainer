package com.labesnoite.pocketpersonaltrainer.config

import com.labesnoite.pocketpersonaltrainer.config.retrofit2.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.7:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun userService(): UserService = retrofit.create(UserService::class.java)
}
