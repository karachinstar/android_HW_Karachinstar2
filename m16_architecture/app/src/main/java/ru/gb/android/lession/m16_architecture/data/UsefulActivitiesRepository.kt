package ru.gb.android.lession.m16_architecture.data

import kotlinx.coroutines.delay
import ru.gb.android.lession.m16_architecture.api.RetrofitInst
import javax.inject.Inject


class UsefulActivitiesRepository @Inject constructor(){
    suspend fun getUsefulActivity(): UsefulActivityDto {
        delay(1000)
        return RetrofitInst.searchUsefulActivityDto.getUsefulActivity()
    }
}