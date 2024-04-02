package ru.gb.android.lession.m16_architecture.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.gb.android.lession.m16_architecture.data.UsefulActivityDto

object RetrofitInst {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchUsefulActivityDto: SearchUsefulActivityDto = retrofit.create(SearchUsefulActivityDto::class.java)
}

interface SearchUsefulActivityDto{
    @GET("/api/activity/")
    suspend fun getUsefulActivity(): UsefulActivityDto
}