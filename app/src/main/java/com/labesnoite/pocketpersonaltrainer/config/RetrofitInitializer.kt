package com.labesnoite.pocketpersonaltrainer.config

import com.labesnoite.pocketpersonaltrainer.config.retrofit2.UserService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun userService(): UserService = retrofit.create(UserService::class.java)
}
