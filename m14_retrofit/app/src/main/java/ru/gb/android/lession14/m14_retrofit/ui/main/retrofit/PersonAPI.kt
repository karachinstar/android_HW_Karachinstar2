package ru.gb.android.lession14.m14_retrofit.ui.main.retrofit

import retrofit2.http.GET

interface PersonAPI {
    @GET("api")
    suspend fun getPersonByRandom(): Person
}