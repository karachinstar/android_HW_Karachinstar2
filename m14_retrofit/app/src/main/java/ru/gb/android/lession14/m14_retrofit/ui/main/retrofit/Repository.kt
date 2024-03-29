package ru.gb.android.lession14.m14_retrofit.ui.main.retrofit

class Repository {

    suspend fun getData() : Person? {
        return RetrofitServices.personApi.getPersonByRandom()
    }
}
