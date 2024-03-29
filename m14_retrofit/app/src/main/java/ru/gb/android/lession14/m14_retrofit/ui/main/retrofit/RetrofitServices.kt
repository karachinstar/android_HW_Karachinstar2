package ru.gb.android.lession14.m14_retrofit.ui.main.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {
private val retrofit = Retrofit.Builder()
    .baseUrl("https://randomuser.me")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val personApi: PersonAPI = retrofit.create(PersonAPI::class.java)
}

